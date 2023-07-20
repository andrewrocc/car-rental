package infrastructure.service;

import infrastructure.dto.UserDTO;
import infrastructure.model.User;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserService {

    @EventListener
    void onApplicationEvent(ContextRefreshedEvent event);

    List<User> getAllUsers(PageRequest pageRequest);

    List<UserDTO> getUsersDTO(PageRequest pageRequest);

    long getCountUsers();

    User getUserById(long id);

    User getUserByEmail(String email);

    User addUser(User u);

    UserDTO getUserInfo(String email);

    UserDTO getUserDTOById(long id);

    User update(long id, UserDTO dto);

    User add(UserDTO userDTO);

    void delete(long id);
}
