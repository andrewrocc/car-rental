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
        Role defaultUserRole = roleService.getReferenceRoleUser();
        User user = User.builder().firstName(userDTO.getFirstName()).lastName(userDTO.getLastName())
                .email(userDTO.getEmail()).paymentCard(userDTO.getPaymentCard())
                .password("{noop}" + userDTO.getPassword()).build();
        user.addRole(defaultUserRole);

        userService.addUser(user);
    }
}
