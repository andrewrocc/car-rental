package infrastructure.controller;

import infrastructure.dto.UserDTO;
import infrastructure.model.User;
import infrastructure.security.AuthenticationService;
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









