package infrastructure.service.implementation;

import infrastructure.dto.UserDto;
import infrastructure.exception.WebServiceException;
import infrastructure.mapper.UserMapper;
import infrastructure.model.Order;
import infrastructure.model.Role;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import infrastructure.service.RoleService;
import infrastructure.service.UserService;
import infrastructure.util.Utils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static infrastructure.util.Utils.EMPTY_STRING;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    @Getter
    private final UserRepository userRepository;

    private final RoleService roleService;

    private final UserMapper userMapper;

    public static String ADMIN_ROLE;

    private void getAdminName() {
        Role admin = roleService.getAdminRole();
        ADMIN_ROLE = admin.getName();
        log.info("admin name is: " + ADMIN_ROLE);
    }

    @Override
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            log.info("context refreshed for get admin name in system.");
            getAdminName();
        }
    }

    @Override
    public List<User> getAllUsers(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<UserDto> getUsersDTO(PageRequest pageRequest) {
        List<User> users = getAllUsers(pageRequest);
        return users.stream().map(userMapper::mapToDto)
                .collect(Collectors.toCollection(() -> new ArrayList<>(users.size())));
    }

    @Override
    public long getCountUsers() {
        return userRepository.count();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> possibleUser = userRepository.findById(id);
        if (possibleUser.isPresent()) {
            User user = possibleUser.get();
            user.getAllOrders();
            return user;
        }
        throw new WebServiceException(EMPTY_STRING, HttpStatus.NOT_FOUND);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new WebServiceException("user " + email + " not found", HttpStatus.NOT_FOUND));
        user.getAllRoles();
        return user;
    }

    @Override
    public User addUser(User u) {
        return userRepository.save(u);
    }

    @Override
    public UserDto getUserInfo(String email) {
        User user = getUserByEmail(email);
        Arrays.stream(user.getAllOrders())
                .peek(Order::getAllUsers)
                .peek(Order::getAllCars)
                .toArray(Order[]::new);
        return userMapper.mapToDto(user);
    }

    @Override
    public UserDto getUserDTOById(long id) {
        if (ADMIN_ROLE == null) {
            getAdminName();
        }
        String authUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User authUser = getUserByEmail(authUserName);
        User user = getUserById(id);
        boolean isRequestUserID = authUserName.equalsIgnoreCase(user.getEmail());
        boolean isAdmin = Utils.hasAdminRole(authUser.getRoles());
        if (isAdmin || isRequestUserID) {
            return userMapper.mapToDto(user);
        } else {
            throw new WebServiceException("access denied", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public User update(long id, UserDto dto) {
        User userReference = userRepository.getReferenceById(id);
        User userForm = userMapper.mapToUser(dto.setOrders(userReference.getOrders().toArray(Order[]::new)));
        if (!Objects.equals(userReference.hashCode(), userForm.hashCode())) {
            return addUser(userForm);
        }
        return userReference;
    }

    @Override
    public User add(UserDto userDTO) {
        User user = userMapper.mapToUser(userDTO);
        return addUser(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}