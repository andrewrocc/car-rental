package infrastructure.controller;

import infrastructure.service.DeleteCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delete-car")
@RequiredArgsConstructor
public class DeleteCarController {

    private final DeleteCarService carService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("id") Long id) {
        carService.delete(id);
    }
}
