package infrastructure.controller;

import infrastructure.dto.CarInfoDTO;
import infrastructure.service.AddCarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

@Controller
@RequiredArgsConstructor
public class AddCarController {

	private final AddCarService carService;

	@GetMapping("/add-car.html")
	public ModelAndView getAddNewCarPage() {
		System.out.println("AddCarController call. " + now());
		return new ModelAndView("add_car", Map.of("carDTO", new CarInfoDTO()));
	}

	@SneakyThrows
	@PostMapping("/add-car.html")
	public String addNewCar(@Valid @ModelAttribute("carDTO") CarInfoDTO carDTO, BindingResult bindingResult,
							@RequestParam("carPhoto") MultipartFile file) {
		if (bindingResult.hasErrors()) {
			return "add_car";
		}
		System.out.println(carDTO);
		carService.addNewCarToApp(carDTO, file.getBytes());
		return "redirect:/car-table.html";
	}
}
