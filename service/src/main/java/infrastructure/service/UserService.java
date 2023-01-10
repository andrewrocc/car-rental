package infrastructure.service;

import infrastructure.dto.UserDTO;
import infrastructure.model.Car;
import infrastructure.model.Order;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Getter
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int getUserSize() {
        return getAllUsers().size();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<User> getUsersByEmail(String email) {
        List<User> users = userRepository.findAllByEmail(email);
        System.out.println("user " + email + " has " + users.get(0).getAllRoles()[0].getName() + " role.");
        return users;
    }

    public UserDTO getUserInfo(String email) {
        User user = userRepository.findUserByEmail(email);
        Order[] orders = user.getAllOrders();
        List<Car> orderCars = new ArrayList<>(orders.length << 1);
        for (Order order : orders) {
            Car[] allCars = order.getAllCars();
            orderCars.addAll(List.of(allCars));
        }
        System.out.println(Arrays.toString(orders));
        System.out.println(orderCars);
        UserDTO u = new UserDTO();
        u.setId(user.getId());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPaymentCard());
        u.setPassword(null);
        u.setOrders(orders);
        return u;
    }
}
