package infrastructure.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static java.time.LocalDateTime.now;

@Slf4j
@Controller
public class LoginController {

    @GetMapping("/login.html")
    public String getLoginPage() {
        log.info("getLoginPage call " + now());
        return "login";
    }
}
