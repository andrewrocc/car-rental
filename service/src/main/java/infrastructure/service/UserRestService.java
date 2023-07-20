package infrastructure.service;

import infrastructure.dto.UserDTO;
import infrastructure.dto.UserDTO_REST;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface UserRestService {

    UserDTO_REST addViaREST(UserDTO_REST requestDTO);

    UserDTO_REST updateViaREST(long id, UserDTO_REST requestDTO);

    UserDTO getUserDTOFromUser(long id);

    List<UserDTO> getAllUsersDTO(PageRequest pageRequest);
}
