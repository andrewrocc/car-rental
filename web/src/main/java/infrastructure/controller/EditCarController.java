package infrastructure.controller;

import infrastructure.dto.CarDto;
import infrastructure.service.CarService;
import infrastructure.service.EditCarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class EditCarController {

    private final EditCarService editCarService;

    private final CarService carService;

    @GetMapping("/edit-car.html")
    public ModelAndView getEditPage(@RequestParam(name = "id") long id) {
        System.out.println("EditCarController call " + now() + ". request param id: " + id);
        return new ModelAndView("edit_car", Map.of("carInfo", carService.getByIdAndPhoto(id)));
    }

    @PostMapping("/edit-car.html")
    public String updateCarInfo(@RequestParam("id") long id, CarDto c) {
        System.out.println("updateCarInfo call " + now() + ". request param id: " + id);
        editCarService.updateInfo(id, c);
        return "redirect:/car-info.html?id=" + id;
    }

    @SneakyThrows
    @PostMapping("/edit-car-photo.html")
    public String updateCarPhoto(@RequestParam("id") long id, @RequestParam("photo") MultipartFile file) {
        System.out.println("updateCarPhoto call " + now() + ". request param id: " + id);
        editCarService.updatePhoto(id, file.getBytes());
        return "redirect:/car-info.html?id=" + id;
    }
}
