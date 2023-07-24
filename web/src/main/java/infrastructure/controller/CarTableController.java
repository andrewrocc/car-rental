package infrastructure.controller;

import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class CarTableController {

    private final CarService carService;

    @GetMapping("/car-table.html")
    public ModelAndView getCarTable(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "101") int size) {
        System.out.println("car table controller: " + now());
        return new ModelAndView( "car_table", Map.of("carList", carService.getAll(PageRequest.of(page, size))));
    }
}



















