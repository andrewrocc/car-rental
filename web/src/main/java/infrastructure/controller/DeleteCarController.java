package infrastructure.controller;

import infrastructure.config.Constant;
import infrastructure.service.RestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class DeleteCarController {

    private final RestService<?> restService;


    @GetMapping("/delete-car.html")
    public String deleteCar(@RequestParam(name = "id") long id) {
        System.out.println("deleteCar call " + now());
        restService.delete(Constant.RESOURCE_CAR_PATH, id);
        return "redirect:/car-table.html";
    }
}
