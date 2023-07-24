package infrastructure.service;

import infrastructure.dto.UserDto;
import infrastructure.dto.UserDtoRest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserRestService {

    UserDtoRest addRest(UserDtoRest requestDTO);

    UserDtoRest updateRest(long id, UserDtoRest requestDTO);

    UserDto getUserById(long id);

    List<UserDto> getAllUsers(PageRequest pageRequest);
}
