package infrastructure.controller;

import infrastructure.GSON.GsonService;
import infrastructure.dto.CarInfoDTO;
import infrastructure.service.CarInfoService;
import infrastructure.service.CarService;
import infrastructure.service.DeleteCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final DeleteCarService deleteCarService;

    private final CarInfoService carInfoService;

    @GetMapping("")
    public ResponseEntity<String> getAllCars() {
        List<CarInfoDTO> carTable = carService.getCarTable();
        String json = GsonService.getJson(carTable);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCarById(@PathVariable("id") Long id) {
        CarInfoDTO car = carInfoService.getCarInfoById(id);
        String json = GsonService.getJson(car);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("id") Long id) {
        deleteCarService.delete(id);
    }
}
