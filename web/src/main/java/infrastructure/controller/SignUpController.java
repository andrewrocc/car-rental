package infrastructure.controller;

import infrastructure.dto.UserSignUpDto;
import infrastructure.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final RegistrationService service;

    @GetMapping("/sign-up.html")
    public ModelAndView getSignUpPage() {
        log.info("getSignUpPage call: " + now());
        return new ModelAndView("sign-up", Map.of("signUpDTO", new UserSignUpDto()));
    }

    @PostMapping("/sign-up.html")               // consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    public String addNewUser(@Valid @ModelAttribute("signUpDTO") UserSignUpDto dto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }
        log.info("addNewUser: " + now());
        log.info(dto.toString());
        service.registrationNew(dto);
        return "redirect:/login.html";
    }
}









