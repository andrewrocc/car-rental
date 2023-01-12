package infrastructure.service;

import infrastructure.dto.UserDTO;
import infrastructure.model.Car;
import infrastructure.model.Order;
import infrastructure.model.Role;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Getter
    private final UserRepository userRepository;

    private final RoleService roleService;

    private static String ADMIN_ROLE;

//    private static final AtomicBoolean onlyOnce = new AtomicBoolean();        // guarantees that the method will be executed once

    private void getAdminName() {
//        if (onlyOnce.getAndSet(true)) return;
        Role admin = roleService.getRoleAdmin();
        ADMIN_ROLE = admin.getName();
        System.out.println("admin name is: " + ADMIN_ROLE);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        getAdminName();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int getUserSize() {
        return getAllUsers().size();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();       // orElse(null)
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void addUser(User u) {
        userRepository.save(u);
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

    public UserDTO getUserDTOById(long id) {
        if (ADMIN_ROLE == null) {
            getAdminName();
        }
        User u = getUserById(id);
        Role[] userRoles = u.getAllRoles();
        System.out.println(Arrays.toString(userRoles));
        boolean isAdmin = Arrays.stream(userRoles).anyMatch(x -> x.getName().equalsIgnoreCase(ADMIN_ROLE));
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setFirstName(u.getFirstName());
        dto.setLastName(u.getLastName());
        dto.setEmail(u.getEmail());
        dto.setPaymentCard(u.getPaymentCard());
        dto.setPassword(u.getPassword());
        dto.setAdmin(isAdmin);
        return dto;
    }
}
