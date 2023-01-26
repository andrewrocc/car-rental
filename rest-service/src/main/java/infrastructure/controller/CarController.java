package infrastructure.controller;

import infrastructure.GSON.GsonService;
import infrastructure.dto.CarBrandDTO;
import infrastructure.dto.CarInfoDTO;
import infrastructure.dto.CarModelDTO;
import infrastructure.model.Car;
import infrastructure.model.CarModel;
import infrastructure.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final CarModelService carModelService;

    private final CarBrandService carBrandService;

    private final DeleteCarService deleteCarService;

    private final CarInfoService carInfoService;

    private final AddCarService addCarService;

    private final EditCarService editCarService;

    @GetMapping("")
    public ResponseEntity<String> getAllCars(@RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "101") int size) {
        List<CarInfoDTO> carTable = carService.getAllCars(PageRequest.of(page, size));
        return new ResponseEntity<>(GsonService.getJson(carTable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCarById(@PathVariable("id") Long id) {
        CarInfoDTO car = carInfoService.getCarInfoById_WithoutPhoto(id);
        return new ResponseEntity<>(GsonService.getJson(car), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody CarInfoDTO carInfoDTO) {
        addCarService.add(carInfoDTO, new byte[0]);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable("id") Long id, @RequestBody CarInfoDTO carInfoDTO) {
        Car car = editCarService.updateCarInfo(id, carInfoDTO);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("id") Long id) {
        deleteCarService.delete(id);
    }

    @GetMapping("/models")
    public ResponseEntity<List<CarModelDTO>> getAllCarModels(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "101") int size) {
        List<CarModelDTO> models = carModelService.getAllCarModels(PageRequest.of(page, size));
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<CarBrandDTO>> getAllCarBrands(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "101") int size) {
        List<CarBrandDTO> models = carBrandService.getAllCarBrands(PageRequest.of(page, size));
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
}
