package infrastructure.service.implementation;

import infrastructure.dto.UserDTO;
import infrastructure.dto.UserDTO_REST;
import infrastructure.mapper.UserMapper;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import infrastructure.service.UserRestService;
import infrastructure.service.UserService;
import infrastructure.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultUserRestService implements UserRestService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final UserService userService;

    @Override
    public UserDTO_REST addViaREST(UserDTO_REST requestDTO) {
        UserDTO userDTO = userMapper.mapToDto(requestDTO);
        User newUser = userService.add(userDTO);
        return userMapper.mapToRest(newUser);
    }

    @Override
    public UserDTO_REST updateViaREST(long id, UserDTO_REST requestDTO) {
        UserDTO userDTO = userMapper.mapToDto(requestDTO).setId(id);
        User newUser = userService.update(id, userDTO);
        return userMapper.mapToRest(newUser);
    }

    @Override
    public UserDTO getUserDTOFromUser(long id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            boolean adminRole = Utils.hasAdminRole(user.getRoles());
            return UserDTO.from_orderDTO(user, adminRole, user.getAllOrders());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public List<UserDTO> getAllUsersDTO(PageRequest pageRequest) {
        List<User> users = userRepository.findAll(pageRequest).getContent();
        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toCollection(() -> new ArrayList<>(users.size())));
    }
}
