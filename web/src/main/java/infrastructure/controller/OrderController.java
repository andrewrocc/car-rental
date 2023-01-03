package infrastructure.controller;

import infrastructure.service.CarInfoService;
import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CarInfoService carInfoService;

    // TODO optimization required
    @GetMapping("/order.html")
    public ModelAndView getOrderPage(@RequestParam("id") long id) {
        return new ModelAndView("order", Map.of("carInfo", carInfoService.getCarInfoById(id)));
    }
}
