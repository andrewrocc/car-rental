package infrastructure.controller;

import infrastructure.GSON.GsonService;
import infrastructure.dto.CarBrandDTO;
import infrastructure.dto.CarDto;
import infrastructure.dto.CarModelDTO;
import infrastructure.mapper.CarMapper;
import infrastructure.model.Car;
import infrastructure.service.CarBrandService;
import infrastructure.service.CarModelService;
import infrastructure.service.CarService;
import infrastructure.service.EditCarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    private final CarModelService carModelService;

    private final CarBrandService carBrandService;

    private final EditCarService editCarService;

    private final CarMapper carMapper;

    @GetMapping("")
    public ResponseEntity<String> getAllCars(@RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "101") int size) {
        List<CarDto> carTable = carService.getAll(PageRequest.of(page, size));
        return new ResponseEntity<>(GsonService.getJson(carTable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCarById(@PathVariable("id") Long id) {
        CarDto carDto = carMapper.mapToDto(carService.getById(id));
        return new ResponseEntity<>(GsonService.getJson(carDto), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody CarDto carDto) {
        carService.add(carDto, new byte[0]);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable("id") Long id, @RequestBody CarDto carDto) {
        Car car = editCarService.updateInfo(id, carDto);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteById(id);
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
