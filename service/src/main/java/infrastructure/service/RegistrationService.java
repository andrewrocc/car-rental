package infrastructure.service;

import infrastructure.dto.UserDTO;
import infrastructure.model.Role;
import infrastructure.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;

    private final RoleService roleService;

    @Transactional
    public void addNewUser(UserDTO userDTO) {
        Role defaultUserRole = roleService.getRoleUser();
        User user = new User();                             // TODO use builder, if create builder in User then get circular dependencies
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPaymentCard(userDTO.getPaymentCard());
        user.setPassword("{noop}" + userDTO.getPassword());
        user.addRole(defaultUserRole);

        userService.addUser(user);
    }
}
