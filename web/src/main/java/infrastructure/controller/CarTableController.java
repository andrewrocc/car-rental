package infrastructure.controller;

import com.google.gson.reflect.TypeToken;
import infrastructure.config.Constant;
import infrastructure.dto.CarInfoDTO;
import infrastructure.service.RestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class CarTableController {

    private final RestService<List<CarInfoDTO>> restService;

    @GetMapping("/car-table.html")
    public ModelAndView getCarTablePage() {
        System.out.println("car table controller: " + now());
        Type type = new TypeToken<List<CarInfoDTO>>() {}.getType();
        List<CarInfoDTO> dataFromRest = restService.getData(Constant.RESOURCE_CAR_PATH, type);
        return new ModelAndView( "car_table", Map.of("carList", dataFromRest));
    }
}



















