package infrastructure.controller;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import infrastructure.dto.CarInfoDTO;
import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;

@Controller
@RequiredArgsConstructor
public class CarTableController {

    private final CarService carService;

    private final String URL_REST_SERVICE = "http://localhost:8080/rest-car";

    @GetMapping("/car-table.html")
    public ModelAndView getCarTablePage() {
        System.out.println("car table controller: " + now());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List> entity = restTemplate.getForEntity(URL_REST_SERVICE + "/cars", List.class);
//        try {
//            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting()
//                    .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).serializeNulls().create();
//            Object o = gson.fromJson(entity.getBody().toString(), List.class);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return new ModelAndView( "car_table", Map.of("carList", requireNonNull(entity.getBody())));         //carService.getCarTable()  // nonNull(entity.getBody()
    }
}



















