package infrastructure.controller;

import infrastructure.mapper.CarMapper;
import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CarInfoPageController {

    private final CarService carService;

    private final CarMapper carMapper;

    @GetMapping("/car-info.html")
    public ModelAndView getCarInfoPage(@RequestParam(name = "id") Long id) {
        log.info("CarInfoPageController call " + now());
        return new ModelAndView("car_info", Map.of("carInfo", carMapper.mapToDto(carService.getById(id))));
    }

    @ResponseBody
    @GetMapping("/image/{car.id}/photo.jpg")
    public byte[] getImage(@PathVariable("car.id") long id) {
        log.info("image: " + id);
        return carService.getById(id).getPhoto().getPhoto();
    }
}












