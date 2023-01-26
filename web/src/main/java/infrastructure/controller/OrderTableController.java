package infrastructure.controller;

import infrastructure.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class OrderTableController {

    private final OrderService orderService;

    @Secured("ROLE_admin")
    @GetMapping("/order-table.html")
    public ModelAndView getOrderTablePage(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "101") int size) {
        System.out.println("getOrderTablePage call:  " + now());
        return new ModelAndView("order_table",
                Map.of("orderList", orderService.getAllOrderTable(PageRequest.of(page, size))));
    }
}
