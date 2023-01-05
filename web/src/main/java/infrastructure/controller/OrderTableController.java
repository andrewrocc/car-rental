package infrastructure.controller;

import infrastructure.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class OrderTableController {

    private final OrderService orderService;

    @GetMapping("/order-table.html")
    public ModelAndView getOrderTablePage() {
        System.out.println("getOrderTablePage call:  " + now());
        return new ModelAndView("order_table", Map.of("orderList", orderService.getOrderTable()));
    }
}
