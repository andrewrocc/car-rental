package infrastructure.service;

import infrastructure.dto.OrderDTO;
import infrastructure.model.Order;
import infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    public List<OrderDTO> getAllOrderTable(PageRequest pageRequest) {
        List<Order> orders = orderRepository.findAll(pageRequest).toList();
        return setInfo(orders);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public OrderDTO getOrderInfoById(String orderString) {
        Map<String, String> orderMap = parseInputString(orderString);
        return OrderDTO.builder().id(Long.parseLong(orderMap.get("id")))
                .price(orderMap.get("price")).date(orderMap.get("date"))
                .numberOfDay(orderMap.get("numberOfDay")).brand(orderMap.get("brand"))
                .model(orderMap.get("model")).login(orderMap.get("login")).build();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private Map<String, String> parseInputString(String orderString) {
        int firstBrace = orderString.indexOf('(');
        int lastBrace = orderString.lastIndexOf(')');
        orderString = orderString.substring(++firstBrace, lastBrace);
        String[] words = orderString.split(", ");
        Map<String, String> orderMap = new HashMap<>(words.length);
        for (String word : words) {
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
