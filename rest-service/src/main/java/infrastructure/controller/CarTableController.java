package infrastructure.controller;

import infrastructure.dto.CarInfoDTO;
import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class CarTableController {

    private final CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarInfoDTO>> getAllCars() {
        List<CarInfoDTO> carTable = carService.getCarTable();
        return new ResponseEntity<>(carTable, HttpStatus.OK);
    }
}
