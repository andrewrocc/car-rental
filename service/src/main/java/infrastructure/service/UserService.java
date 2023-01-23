package infrastructure.service;

import infrastructure.dto.UserDTO;
import infrastructure.model.Order;
import infrastructure.model.Role;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Getter
    private final UserRepository userRepository;

    private final RoleService roleService;

    private static String ADMIN_ROLE;

//    private static final AtomicBoolean onlyOnce = new AtomicBoolean();

    private void getAdminName() {
//        if (onlyOnce.getAndSet(true)) return;                     // guarantees that the method will be executed once
        Role admin = roleService.getReferenceRoleAdmin();
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

    public List<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).stream().toList();
    }

    public long getCountUsers() {
        return userRepository.count();
    }

    public int getUserSize() {
        return getAllUsers().size();
    }

    public User getUserById(long id) {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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

    public boolean hasAdminRole(Collection<Role> list) {
        return list.stream().anyMatch(x -> x.getName().equals(ADMIN_ROLE));
    }

    public List<User> getUsersByEmail(String email) {
        List<User> users = userRepository.findAllByEmail(email);
        System.out.println("user " + email + " has " + users.get(0).getAllRoles()[0].getName() + " role.");
        return users;
    }

    public UserDTO getUserInfo(String email) {
        User user = userRepository.findUserByEmail(email);
        Order[] orders = user.getAllOrders();
        boolean isAdmin = hasAdminRole(user.getRoles());
        orders = isAdmin ? Arrays.stream(orders).peek(Order::getAllCars).toArray(Order[]::new) :
                Arrays.stream(orders).filter(x -> x.getCars().size() > 0).toArray(Order[]::new);
        return UserDTO.from(user, orders);
    }

    public UserDTO getUserDTOById(long id) {
        if (ADMIN_ROLE == null) {
            getAdminName();
        }
        String authUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User authUser = getUserByEmail(authUserEmail);
        User userById = getUserById(id);
        boolean isRequestUserID = authUserEmail.equalsIgnoreCase(userById.getEmail());
        boolean isAdmin = hasAdminRole(authUser.getRoles());
        if (isAdmin || isRequestUserID) {
            boolean currentRole = hasAdminRole(userById.getRoles());
            return UserDTO.from(userById, currentRole);
        }
        return null;
    }

    public void update(long id, UserDTO dto) {
        User userReference = getUserReferenceById(id);
        String parsePassword = dto.getPassword().contains("{noop}") ? dto.getPassword() :"{noop}" + dto.getPassword();
        User userForm = User.builder().id(dto.getId()).firstName(dto.getFirstName()).lastName(dto.getLastName())
                .email(dto.getEmail()).paymentCard(dto.getPaymentCard()).password(parsePassword)
                .orders(userReference.getOrders()).build();
        userForm.addRole(dto.isAdmin() ? roleService.getReferenceRoleAdmin() : roleService.getReferenceRoleUser());

        if (!(userReference.hashCode() == userForm.hashCode())) {
            addUser(userForm);
        }
    }

    public void add(UserDTO userDTO) {
        User userBuilder = User.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName())
                .email(userDTO.getEmail()).paymentCard(userDTO.getPaymentCard())
                .password("{noop}" + userDTO.getPassword()).build();
        userBuilder.addRole(userDTO.isAdmin() ? roleService.getReferenceRoleAdmin() : roleService.getReferenceRoleUser());
        addUser(userBuilder);
    }
}
