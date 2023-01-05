package infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static java.time.LocalDateTime.now;

@Controller
public class LoginController {

    @GetMapping("/login.html")
    public String getLoginPage() {
        System.out.println("getLoginPage call " + now());
        return "login";
    }
}
