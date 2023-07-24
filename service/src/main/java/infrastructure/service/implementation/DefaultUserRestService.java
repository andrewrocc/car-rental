package infrastructure.service.implementation;

import infrastructure.dto.UserDto;
import infrastructure.dto.UserDtoRest;
import infrastructure.exception.WebServiceException;
import infrastructure.mapper.UserMapper;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import infrastructure.service.UserRestService;
import infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static infrastructure.util.Utils.EMPTY_STRING;

@Service
@RequiredArgsConstructor
public class DefaultUserRestService implements UserRestService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final UserService userService;

    @Override
    public UserDtoRest addRest(UserDtoRest requestDTO) {
        UserDto userDTO = userMapper.mapToDto(requestDTO);
        User newUser = userService.add(userDTO);
        return userMapper.mapToRest(newUser);
    }

    @Override
    public UserDtoRest updateRest(long id, UserDtoRest requestDTO) {
        UserDto userDTO = userMapper.mapToDto(requestDTO).setId(id);
        User newUser = userService.update(id, userDTO);
        return userMapper.mapToRest(newUser);
    }

    @Override
    public UserDto getUserById(long id) {
        Optional<User> possibleUser = userRepository.findById(id);
        if (possibleUser.isPresent()) {
            User user = possibleUser.get();
            return userMapper.mapToDtoByOrderDto(user);
        }
        throw new WebServiceException(EMPTY_STRING, HttpStatus.NOT_FOUND);
    }

    @Override
    public List<UserDto> getAllUsers(PageRequest pageRequest) {
        List<User> users = userRepository.findAll(pageRequest).getContent();
        return users.stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toCollection(() -> new ArrayList<>(users.size())));
    }
}
