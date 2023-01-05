package infrastructure.controller;

import infrastructure.dto.CarInfoDTO;
import infrastructure.service.AddCarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import static java.time.LocalDateTime.now;

@Controller
@RequiredArgsConstructor
public class AddCarController {

	private final AddCarService carService;

	@GetMapping("/add-car.html")
	public String getAddNewCarPage() {
		System.out.println("AddCarController call. " + now());
		return "add_car";
	}

	@SneakyThrows
	@PostMapping("/add-car.html")
	public String addNewCar(@RequestParam("carPhoto") MultipartFile file, CarInfoDTO c) {
		System.out.println(c);
		carService.addNewCarToApp(c, file.getBytes());
		return "redirect:/car-table.html";
	}
}
