package infrastructure.service.implementation;

import infrastructure.dto.UserSignUpDto;
import infrastructure.dto.UserDto;
import infrastructure.mapper.UserMapper;
import infrastructure.model.Role;
import infrastructure.model.User;
import infrastructure.service.RegistrationService;
import infrastructure.service.RoleService;
import infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DefaultRegistrationService implements RegistrationService {

    private final UserService userService;

    private final RoleService roleService;

    private final ModelMapper modelMapper;

    private final UserMapper userMapper;

    @Override
    @Transactional
    public void registrationNew(UserSignUpDto userSignUpDTO) {
        UserDto userDto = userMapper.mapToDto(userSignUpDTO);
        Role defaultUserRole = roleService.getUserRole();
        User user = userMapper.mapToUser(userDto);
        user.addRole(defaultUserRole);

        userService.addUser(user);
    }
}
