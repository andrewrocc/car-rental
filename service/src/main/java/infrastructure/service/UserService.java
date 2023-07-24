package infrastructure.service;

import infrastructure.dto.UserDto;
import infrastructure.model.User;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {

    @EventListener
    void onApplicationEvent(ContextRefreshedEvent event);

    void delete(long id);

    long getCountUsers();

    List<User> getAllUsers(PageRequest pageRequest);

    List<UserDto> getUsersDTO(PageRequest pageRequest);

    User getUserById(long id);

    User getUserByEmail(String email);

    User addUser(User u);

    UserDto getUserInfo(String email);

    UserDto getUserDTOById(long id);

    User update(long id, UserDto dto);

    User add(UserDto userDTO);
}