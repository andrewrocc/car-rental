package infrastructure.controller;

import infrastructure.dto.UserDTO;
import infrastructure.dto.UserDTO_REST;
import infrastructure.service.UserRestService;
import infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRestService userRestService;

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                                     @RequestParam(value = "size", defaultValue = "101") int size) {
        List<UserDTO> users = userRestService.getAllUsersDTO(PageRequest.of(page - 1, size));

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
        UserDTO user = userRestService.getUserDTOFromUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO_REST> addUser(@RequestBody UserDTO_REST userDTO) {
        UserDTO_REST userDTOREST = userRestService.addViaREST(userDTO);
        return new ResponseEntity<>(userDTOREST, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO_REST> updateUser(@PathVariable("id") Long id,
                                                   @RequestBody UserDTO_REST userDTO) {
        UserDTO_REST userDTOREST = userRestService.updateViaREST(id, userDTO);
        return new ResponseEntity<>(userDTOREST, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
