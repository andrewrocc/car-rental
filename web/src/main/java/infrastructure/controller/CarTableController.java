package infrastructure.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;
import infrastructure.dto.CarInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Objects.requireNonNull;

@Controller
@RequiredArgsConstructor
public class CarTableController {

    private final String URL_REST_SERVICE = "http://localhost:8080/rest-car";

    @GetMapping("/car-table.html")
    public ModelAndView getCarTablePage() {
        System.out.println("car table controller: " + now());
        RestTemplate restTemplate = new RestTemplate();
        final String rawData = restTemplate.getForObject(URL_REST_SERVICE + "/cars", String.class);
        Type type = new TypeToken<List<CarInfoDTO>>() {}.getType();
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting()
                    .setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).serializeNulls().create();
        List<CarInfoDTO> fromJson = gson.fromJson(rawData, type);
        return new ModelAndView( "car_table", Map.of("carList", requireNonNull(fromJson)));
    }
}



















