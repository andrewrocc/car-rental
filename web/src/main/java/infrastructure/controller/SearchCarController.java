package infrastructure.controller;

import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SearchCarController {

    private final CarService carServiceImpl;

    @PostMapping("/search.html")
    public ModelAndView getResultSearch(String keyword) {
        return new ModelAndView("search_result",
                Map.of("cars", carServiceImpl.getByBrandOrModelName(keyword)));
    }
}
