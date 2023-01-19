package infrastructure.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;
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
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarTableRestController {

    private final CarService carService;

    @GetMapping("")
    public ResponseEntity<String> getAllCars() {        // List<CarInfoDTO>
        List<CarInfoDTO> carTable = carService.getCarTable();
        String jsonResponse = new GsonBuilder().setPrettyPrinting()
                .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).serializeNulls().create()
                .toJson(carTable, new TypeToken<List<CarInfoDTO>>() {}.getType());

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}
