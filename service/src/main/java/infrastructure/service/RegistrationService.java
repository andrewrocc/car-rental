package infrastructure.service;

import infrastructure.dto.UserDTO;
import infrastructure.model.Role;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;

    private final RoleService roleService;

    @Transactional
    public void addNewUser(UserDTO u) {
        Role newUserRole = roleService.getRoleUser();
        User user = new User();
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setEmail(u.getEmail());
        user.setPaymentCard(u.getPaymentCard());
        user.setPassword("{noop}" + u.getPassword());
        user.addRole(newUserRole);

        userService.addUser(user);
    }
}
