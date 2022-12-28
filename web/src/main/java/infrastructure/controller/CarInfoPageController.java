package infrastructure.controller;

import infrastructure.service.CarInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CarInfoPageController {

    private final CarInfoService carInfoService;

    @GetMapping("/car-info.html")
    public ModelAndView getCarInfoPage(@RequestParam(name = "id") Long id) {
        System.out.println("CarInfoPageController controller");
        return new ModelAndView("car_info", Map.of("carInfo", carInfoService.getCarInfoById(id)));
    }
}
