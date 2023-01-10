package infrastructure.controller;

import infrastructure.dto.UserDTO;
import infrastructure.service.OrderService;
import infrastructure.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    private final OrderService orderService;

    @GetMapping("/account.html")
    public ModelAndView getAccountPage() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("getAccountPage call: " + now() + ". with login name: " + login);
        UserDTO userInfo = userService.getUserInfo(login);
        return new ModelAndView("account", Map.of("userInfo", userInfo,
                "orderInfo", orderService.setInfo(Arrays.asList(userInfo.getOrders()))));
    }
}
