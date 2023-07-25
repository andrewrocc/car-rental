package infrastructure.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import static java.time.LocalDateTime.now;

@Slf4j
@Controller
public class HomePageController {

	@GetMapping({"/index.html", "/"})
	public ModelAndView getHomePage() {
		log.info("getHomePage: " + now());
		return new ModelAndView("index");
	}
}
