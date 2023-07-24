package infrastructure.controller;

import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.time.LocalDateTime.now;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeleteCarController {

    private final CarService carService;

    @GetMapping("/delete-car.html")
    public String delete(@RequestParam(name = "id") long id) {
        log.info("delete car call " + now());
        carService.deleteById(id);
        return "redirect:/car-table.html";
    }
}
