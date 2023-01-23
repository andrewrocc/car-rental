package infrastructure.controller;

import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SearchCarController {

    private final CarService carService;

    @PostMapping("/search.html")
    public ModelAndView getResultSearch(String keyword) {
        List<CarInfoDTO> cars = carService.getCarByBrandOrModelName(keyword);
        return new ModelAndView("search_result", Map.of("cars", cars));
    }
}
