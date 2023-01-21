package infrastructure.controller;

import com.google.gson.reflect.TypeToken;
import infrastructure.config.Constant;
import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.service.CarInfoService;
import infrastructure.service.CarService;
import infrastructure.service.RestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class CarInfoPageController {

    private final CarInfoService carInfoService;

    private final CarService carService;

    private final RestService<CarInfoDTO> restService;

    @GetMapping("/car-info.html")
    public ModelAndView getCarInfoPage(@RequestParam(name = "id") Long id) {
        System.out.println("CarInfoPageController call " + now());
        Type type = new TypeToken<CarInfoDTO>() {}.getType();
        CarInfoDTO dataFromRest = restService.getData(Constant.RESOURCE_CAR_PATH + "/" + id, type);
        return new ModelAndView("car_info", Map.of("carInfo", dataFromRest));
    }

    @ResponseBody
    @GetMapping("/image/{car.id}/photo.jpg")
    public byte[] getImage(@PathVariable("car.id") long id) {
        System.out.println("image: " + id);
        Car car = carService.getCarById(id);
        return car.getPhoto().getPhoto();
    }
}












