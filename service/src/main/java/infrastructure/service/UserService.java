package infrastructure.service;

import infrastructure.dto.UserDTO;
import infrastructure.dto.UserDTO_REST;
import infrastructure.model.Order;
import infrastructure.model.Role;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.security.ProviderException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Getter
    private final UserRepository userRepository;

    private final RoleService roleService;

    private final ModelMapper modelMapper;

    private static String ADMIN_ROLE;

    private void getAdminName() {
//        Role admin = roleService.getReferenceRoleAdmin();
        Role admin = roleService.getAdminRole();
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

    public User getUserById(long id) {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        user.getAllRoles();
        return user;
    }

    public User getUserReferenceById(long id) {
        return userRepository.getReferenceById(id);
    }

    public User addUser(User u) {
        return userRepository.save(u);
    }

    public boolean hasAdminRole(Collection<Role> list) {
        return list.stream().anyMatch(x -> x.getName().equals(ADMIN_ROLE));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public UserDTO getUserInfo(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        Order[] orders = user.getAllOrders();
        Arrays.stream(orders).peek(Order::getAllUsers).toList();
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

    public User update(long id, UserDTO dto) {
        User userReference = getUserReferenceById(id);
        String parsePassword = dto.getPassword().contains("{noop}") ? dto.getPassword() :"{noop}" + dto.getPassword();
        User userForm = User.builder().id(dto.getId()).firstName(dto.getFirstName()).lastName(dto.getLastName())
                .email(dto.getEmail()).paymentCard(dto.getPaymentCard()).password(parsePassword)
                .orders(userReference.getOrders()).build();
        userForm.addRole(dto.isAdmin() ? roleService.getAdminRole() : roleService.getUserRole());

        if (!(userReference.hashCode() == userForm.hashCode())) {
            return addUser(userForm);
        }
        throw new ProviderException("Try another one time!");
    }

    public User add(UserDTO userDTO) {
        User user = User.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName())
                .email(userDTO.getEmail()).paymentCard(userDTO.getPaymentCard())
                .password("{noop}" + userDTO.getPassword()).build();
        user.addRole(userDTO.isAdmin() ? roleService.getAdminRole() : roleService.getUserRole());
        return addUser(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    //region methods for REST services
    public UserDTO_REST addViaREST(UserDTO_REST requestDTO) {
        UserDTO userDTO = modelMapper.map(requestDTO, UserDTO.class);
        User newUser = add(userDTO);
        return modelMapper.map(newUser, UserDTO_REST.class);
    }

    public UserDTO_REST updateViaREST(long id, UserDTO_REST requestDTO) {
        UserDTO userDTO = modelMapper.map(requestDTO, UserDTO.class);
        userDTO.setId(id);
        User newUser = update(id, userDTO);
        return modelMapper.map(newUser, UserDTO_REST.class);
    }

    public UserDTO getUserDTOFromUser(long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            boolean adminRole = hasAdminRole(user.getRoles());
            return UserDTO.from_orderDTO(user, adminRole, user.getAllOrders());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public List<UserDTO> getAllUsersDTO(PageRequest pageRequest) {
        List<User> users = userRepository.findAll(pageRequest).stream().toList();
        return users.stream()
                .map(user -> UserDTO.from_orderDTO(user, hasAdminRole(user.getRoles()), user.getAllOrders()))
                .collect(Collectors.toCollection(() -> new ArrayList<>(users.size())));
    }
    //endregion
}