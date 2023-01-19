package infrastructure.controller;

import infrastructure.service.CarService;
import infrastructure.service.DeleteCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class DeleteCarController {

    private final DeleteCarService deleteService;

    @GetMapping("/delete-car.html")
    public String deleteCar(@RequestParam(name = "id") long id) {
        System.out.println("deleteCar call " + now());
        deleteService.delete(id);
        return "redirect:/car-table.html";
    }
}
