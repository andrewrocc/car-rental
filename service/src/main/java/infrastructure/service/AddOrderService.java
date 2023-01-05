package infrastructure.service;

import infrastructure.dto.OrderInfoDTO;
import infrastructure.model.Car;
import infrastructure.model.Order;
import infrastructure.model.User;
import infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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

    public void add(long id, LocalDate startDate, short numberOfDay, OrderInfoDTO orderDTO) {
        Car car = carService.getCarById(id);
        User user = userService.getUserByEmail("admin@rentcar.com");        // TODO get userEmail from user session
        Order order = new Order();
        order.setPrice(new BigDecimal(orderDTO.getPrice()));
        order.setDate(startDate);
        order.setNumberOfDay(numberOfDay);
        order.addCar(car);
        order.addUser(user);
        orderRepository.saveAndFlush(order);
        System.out.println("order was created! " + now());
    }
}
