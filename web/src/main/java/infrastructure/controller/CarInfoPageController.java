package infrastructure.controller;

import infrastructure.model.Car;
import infrastructure.service.CarInfoService;
import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class CarInfoPageController {

    private final CarInfoService carInfoService;

    private final CarService carService;

    @GetMapping("/car-info.html")
    public ModelAndView getCarInfoPage(@RequestParam(name = "id") Long id) {
        System.out.println("CarInfoPageController call " + now());
        return new ModelAndView("car_info", Map.of("carInfo", carInfoService.getCarInfoById(id)));
    }

    @ResponseBody
    @GetMapping("/image/{car.id}/photo.jpg")
    public byte[] getImage(@PathVariable("car.id") long id) {
        System.out.println("image: " + id);
        Car car = carService.getCarById(id);
        return car.getPhoto().getPhoto();
    }
}












