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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

//    private static final AtomicBoolean onlyOnce = new AtomicBoolean();

    private static final AtomicBoolean isAdmin = new AtomicBoolean();

    private void getAdminName() {
//        if (onlyOnce.getAndSet(true)) return;                     // guarantees that the method will be executed once
        Role admin = roleService.getRoleAdmin();
        ADMIN_ROLE = admin.getName();
        System.out.println("admin name is: " + ADMIN_ROLE);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            getAdminName();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public int getUserSize() {
        return getAllUsers().size();
    }

    public User getUserById(long id) {
        try {
            return userRepository.findById(id).get();       // orElse(null)
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User getUserReferenceById(long id) {
        return userRepository.getReferenceById(id);
    }

    public void addUser(User u) {
        userRepository.save(u);
    }

    public boolean isAdmin() {
        String authUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User authUser = getUserByEmail(authUserEmail);
        return authUser.getRoles().stream().anyMatch(x -> x.getName().equals(ADMIN_ROLE));
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
        String authUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User authUser = getUserByEmail(authUserEmail);
        User u = getUserById(id);
        boolean isRequestUserID = authUserEmail.equalsIgnoreCase(u.getEmail());
        boolean isAdmin = authUser.getRoles().stream()
                .anyMatch(x -> x.getName().equals(ADMIN_ROLE));
        if (isAdmin || isRequestUserID) {
            UserDTO dto = new UserDTO();
            dto.setId(u.getId());
            dto.setFirstName(u.getFirstName());
            dto.setLastName(u.getLastName());
            dto.setEmail(u.getEmail());
            dto.setPaymentCard(u.getPaymentCard());
            dto.setPassword(u.getPassword());
            boolean currentRole = u.getRoles().stream().anyMatch(x -> x.getName().equals(ADMIN_ROLE));
            dto.setAdmin(currentRole);
            return dto;
        }
        return null;
    }

    public void updateUserInfo(long id, UserDTO dto) {
        User userReference = getUserReferenceById(id);
        User userForm = new User();
        userForm.setId(dto.getId());
        userForm.setFirstName(dto.getFirstName());
        userForm.setLastName(dto.getLastName());
        userForm.setEmail(dto.getEmail());
        userForm.setPaymentCard(dto.getPaymentCard());
        userForm.setPassword(dto.getPassword());
        userForm.setOrders(userReference.getOrders());
        boolean isUserReferenceAdmin = Arrays.stream(userReference.getAllRoles()).anyMatch(x -> x.getName().equals(ADMIN_ROLE));
        userForm.addRole(dto.isAdmin() == isUserReferenceAdmin ? roleService.getRoleAdmin() : roleService.getRoleUser());

        if (!(userReference.hashCode() == userForm.hashCode())) {
            addUser(userForm);
        }
    }
}
