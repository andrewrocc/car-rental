package infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static java.time.LocalDateTime.*;

@Controller
public class CarTableController {

    @GetMapping("car-table.html")
    public String getCatTablePage() {
        System.out.println("car table controller " + now());
        return "car_table";
    }
}
