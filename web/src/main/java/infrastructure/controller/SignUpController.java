package infrastructure.controller;

import infrastructure.model.User;
import infrastructure.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final RegistrationService service;

    @GetMapping("/sign-up.html")
    public String getSignUpPage() {
        System.out.println("sign up controller " + now());
        return "sign-up";
    }

    @PostMapping(value = "/sign-up.html")               // consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    public String addNewUser(User user) {
        System.out.println("post registration controller");
        System.out.println(user);
        service.addNewUser(user);
        return "redirect:/index.html";
    }
}









