package infrastructure.service;

import infrastructure.dto.OrderDTO;
import infrastructure.model.Car;
import infrastructure.model.Order;
import infrastructure.model.User;
import infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalDateTime.now;

@Service
@Transactional
@RequiredArgsConstructor
public class AddOrderService {

    private final OrderRepository orderRepository;

    private final CarService carService;

    private final UserService userService;

    public void add(long id, LocalDate startDate, short numberOfDay, OrderDTO orderDTO) {
        Car car = carService.getCarById(id);
        User user = userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Order order = Order.builder().price(new BigDecimal(orderDTO.getPrice()))
                .date(startDate).numberOfDay(numberOfDay).build();
        order.addCar(car);
        order.addUser(user);
        orderRepository.saveAndFlush(order);
        System.out.println("order was created! " + now());
    }
}
