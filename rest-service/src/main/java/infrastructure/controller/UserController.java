package infrastructure.controller;

import infrastructure.dto.UserDto;
import infrastructure.dto.UserDtoRest;
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
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                                     @RequestParam(value = "size", defaultValue = "101") int size) {
        List<UserDto> users = userRestService.getAllUsers(PageRequest.of(page - 1, size));

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDto user = userRestService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDtoRest> addUser(@RequestBody UserDtoRest userDTO) {
        UserDtoRest userDTOREST = userRestService.addRest(userDTO);
        return new ResponseEntity<>(userDTOREST, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDtoRest> updateUser(@PathVariable("id") Long id,
                                                  @RequestBody UserDtoRest userDTO) {
        UserDtoRest userDTOREST = userRestService.updateRest(id, userDTO);
        return new ResponseEntity<>(userDTOREST, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
