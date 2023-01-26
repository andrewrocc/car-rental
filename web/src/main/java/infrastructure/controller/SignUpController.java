package infrastructure.controller;

import infrastructure.dto.SignUpDTO;
import infrastructure.dto.UserDTO;
import infrastructure.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final RegistrationService service;

    @GetMapping("/sign-up.html")
    public ModelAndView getSignUpPage() {
        System.out.println("getSignUpPage call: " + now());
        return new ModelAndView("sign-up", Map.of("signUpDTO", new SignUpDTO()));
    }

    @PostMapping("/sign-up.html")               // consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    public String addNewUser(@Valid @ModelAttribute("signUpDTO") SignUpDTO dto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }
        System.out.println("addNewUser: " + now());
        System.out.println(dto);
        service.addNewUser(dto);
        return "redirect:/login.html";
    }
}









