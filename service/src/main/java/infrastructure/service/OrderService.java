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
        Map<String, String> orderMap = parseInputString(orderString);
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(Long.parseLong(orderMap.get("id")));
        orderDTO.setPrice(orderMap.get("price"));
        orderDTO.setDate(orderMap.get("date"));
        orderDTO.setNumberOfDay(orderMap.get("numberOfDay"));
        orderDTO.setBrand(orderMap.get("brand"));
        orderDTO.setModel(orderMap.get("model"));
        orderDTO.setLogin(orderMap.get("login"));

        return orderDTO;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private Map<String, String> parseInputString(String orderString) {
        int firstBrace = orderString.indexOf('(');
        int lastBrace = orderString.lastIndexOf(')');
        orderString = orderString.substring(++firstBrace, lastBrace);
        String[] words = orderString.split(", ");
        Map<String, String> orderMap = new HashMap<>(words.length);
        for (String word : words) {                                     // TODO to lambda
            String[] temp = word.split("=");
            orderMap.put(temp[0], temp[1]);
        }
        return orderMap;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<OrderDTO> setInfo(List<Order> orders) {
        List<OrderDTO> orderList = new ArrayList<>(orders.size());
        for (Order order : orders) {
            OrderDTO orderDTO = OrderDTO.from(order);
            orderList.add(orderDTO);
        }
        return orderList;
    }
}
