package infrastructure.controller;

import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.*;

@Controller
@RequiredArgsConstructor
public class CarTableController {

    private final CarService carService;

    @GetMapping("/car-table.html")
    public ModelAndView getCatTablePage() {
        System.out.println("car table controller " + now());
        return new ModelAndView( "car_table", Map.of("carList", carService.getCarTable()));
    }
}
