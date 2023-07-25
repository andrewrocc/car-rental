package infrastructure.service.implementation;

import infrastructure.dto.OrderDto;
import infrastructure.mapper.OrderMapper;
import infrastructure.model.Car;
import infrastructure.model.Order;
import infrastructure.model.User;
import infrastructure.repository.OrderRepository;
import infrastructure.service.CarService;
import infrastructure.service.OrderService;
import infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final CarService carService;

    private final UserService userService;

    @Override
    public List<OrderDto> getAllOrderTable(PageRequest pageRequest) {
        List<Order> orders = orderRepository.findAll(pageRequest).getContent();
        return setInfo(orders);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public OrderDto getOrderInfoById(String orderString) {
        Map<String, String> orderMap = extractData(orderString);
        return new OrderDto().setId(Long.parseLong(orderMap.get("id")))
                .setPrice(orderMap.get("price"))
                .setDate(orderMap.get("date"))
                .setNumberOfDay(orderMap.get("numberOfDay"))
                .setBrand(orderMap.get("brand"))
                .setModel(orderMap.get("model"))
                .setLogin(orderMap.get("login"));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private Map<String, String> extractData(String orderString) {
        int firstBrace = orderString.indexOf('(');
        int lastBrace = orderString.lastIndexOf(')');
        orderString = orderString.substring(++firstBrace, lastBrace);
        String[] words = orderString.split(", ");
        return Arrays.stream(words)
                .map(word -> word.split("="))
                .collect(Collectors.toMap(
                        temp -> temp[0], temp -> temp[1], (a, b) -> b, () -> new HashMap<>(words.length)));
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<OrderDto> setInfo(List<Order> orders) {
        return orders.stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toCollection(() -> new ArrayList<>(orders.size())));
    }

    @Override
    public void add(long id, LocalDate startDate, short numberOfDay, OrderDto orderDTO) {
        Car car = carService.getById(id);
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Order order = new Order().setPrice(new BigDecimal(orderDTO.getPrice()))
                .setDate(startDate)
                .setNumberOfDay(numberOfDay);
        order.addCar(car);
        order.addUser(user);
        orderRepository.saveAndFlush(order);
        log.info("order was created! " + now());
    }
}
