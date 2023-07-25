package infrastructure.controller;

import infrastructure.dto.OrderDto;
import infrastructure.service.CarService;
import infrastructure.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final CarService carService;

    @GetMapping("/order.html")
    public ModelAndView getOrderPage(@RequestParam("id") long id) {
        log.info("getOrderPage call " + now());
        return new ModelAndView("order", Map.of("carInfo", carService.getByIdAndPhoto(id)));
    }

    @Secured({"ROLE_admin", "ROLE_user"})
    @GetMapping("/order-info.html")
    public ModelAndView getOrderPageById(@RequestParam("id") long id, @RequestParam("info") String order) {
        log.info("getOrderPageById call: id = " + id + " " + now());
        return new ModelAndView("order_info", Map.of("orderList", orderService.getOrderInfoById(order)));
    }

    @PostMapping("/create-order.html")
    public String addNewOrder(@RequestParam("id") long id,
                              @RequestParam("startDay") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDay,
                              @RequestParam("numberOfDays") short numberOfDays, OrderDto orderInfo) {
        log.info("addNewOrder call " + now() + " " + orderInfo);
        orderService.add(id, startDay, numberOfDays, orderInfo);
        return "redirect:/account.html";
    }
}
