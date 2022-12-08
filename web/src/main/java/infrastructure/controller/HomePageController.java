package infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static java.time.LocalDateTime.now;

@Controller
public class HomePageController {

	@GetMapping("/index.html")
	public ModelAndView getHomePage() {
		System.out.println("home page " + now());
		return new ModelAndView("index");
	}
}
