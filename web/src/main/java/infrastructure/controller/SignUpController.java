package infrastructure.controller;

import infrastructure.dto.UserDTO;
import infrastructure.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final RegistrationService service;

    @GetMapping("/sign-up.html")
    public String getSignUpPage() {
        System.out.println("getSignUpPage call: " + now());
        return "sign-up";
    }

    @PostMapping("/sign-up.html")               // consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    public String addNewUser(UserDTO dto) {
        System.out.println("addNewUser: " + now());
        System.out.println(dto);
        service.addNewUser(dto);
        return "redirect:/login.html";
    }
}









