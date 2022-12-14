package infrastructure.controller;

import infrastructure.dto.OrderDTO;
import infrastructure.dto.OrderInfoDTO;
import infrastructure.service.AddOrderService;
import infrastructure.service.CarInfoService;
import infrastructure.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final CarInfoService carInfoService;

    private final AddOrderService addOrderService;

    private final OrderService orderService;

    @GetMapping("/order.html")
    public ModelAndView getOrderPage(@RequestParam("id") long id) {
        System.out.println("getOrderPage call " + now());
        return new ModelAndView("order", Map.of("carInfo", carInfoService.getCarInfoById(id)));
    }

    @GetMapping("/order-info.html")
    public ModelAndView getOrderPageById(@RequestParam("id") long id, @RequestParam("info") String order) {
        System.out.println("getOrderPageById call: id = " + id + " " + now());
        return new ModelAndView("order_info", Map.of("orderList", orderService.getOrderInfoById(order)));
    }

    @PostMapping("/create-order.html")
    public String addNewOrder(@RequestParam("id") long id,
                              @RequestParam("startDay") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDay,
                              @RequestParam("numberOfDays") short numberOfDays, OrderInfoDTO orderInfo) {
        System.out.println("addNewOrder call " + now() + " " + orderInfo);
        addOrderService.add(id, startDay, numberOfDays, orderInfo);
        return "redirect:/order-table.html";
    }
}
