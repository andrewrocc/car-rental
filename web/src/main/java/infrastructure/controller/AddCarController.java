package infrastructure.controller;

import infrastructure.dto.CarDto;
import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AddCarController {

	private final CarService carService;

	@Secured("ROLE_admin")
	@GetMapping("/add-car.html")
	public ModelAndView getAddNewCarPage() {
		log.info("AddCarController call. " + now());
		return new ModelAndView("add_car", Map.of("carDTO", new CarDto()));
	}

	@SneakyThrows
	@PostMapping("/add-car.html")
	public String addNewCar(@Valid @ModelAttribute("carDTO") CarDto carDTO, BindingResult bindingResult,
							@RequestParam("carPhoto") MultipartFile file) {
		if (bindingResult.hasErrors()) {
			return "add_car";
		}
		log.info(carDTO.toString());
		carService.add(carDTO, file.getBytes());
		return "redirect:/car-table.html";
	}
}
