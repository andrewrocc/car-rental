package infrastructure.service;

import infrastructure.dto.OrderDTO;
import infrastructure.model.Order;
import infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDTO> getAllOrderTable() {
        List<Order> orders = orderRepository.findAll();
        return setInfo(orders);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public OrderDTO getOrderInfoById(String orderString) {
        OrderDTO o = new OrderDTO();
        int firstBrace = orderString.indexOf('(');
        int lastBrace = orderString.lastIndexOf(')');
        orderString = orderString.substring(++firstBrace, lastBrace);
        String[] words = orderString.split(", ");
        Map<String, String> orderMap = new HashMap<>(words.length);
        for (String word : words) {
            String[] temp = word.split("=");
            orderMap.put(temp[0], temp[1]);
        }
        o.setId(Long.parseLong(orderMap.get("id")));
        o.setPrice(orderMap.get("price"));
        o.setDate(orderMap.get("date"));
        o.setNumberOfDay(orderMap.get("numberOfDay"));
        o.setBrand(orderMap.get("brand"));
        o.setModel(orderMap.get("model"));
        o.setLogin(orderMap.get("login"));

        return o;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<OrderDTO> setInfo(List<Order> orders) {
        List<OrderDTO> orderList = new ArrayList<>(orders.size());
        for (Order order : orders) {
            OrderDTO o = new OrderDTO();
            o.setId(order.getId());
            o.setPrice(order.getPrice().toString());
            o.setDate(order.getDate().toString());
            o.setNumberOfDay(String.valueOf(order.getNumberOfDay()));
            o.setLogin(order.getAllUsers()[0].getEmail());
            o.setBrand(order.getAllCars()[0].getCarBrand().getBrandName());
            o.setModel(order.getAllCars()[0].getCarModel().getModelName());
            orderList.add(o);
        }
        return orderList;
    }
}
