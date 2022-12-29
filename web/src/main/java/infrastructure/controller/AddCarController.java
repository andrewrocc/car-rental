package infrastructure.controller;

import infrastructure.dto.CarTable;
import infrastructure.service.AddCarService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class AddCarController {

	private final AddCarService carService;

	@GetMapping("/add-car.html")
	public String getAddNewCarPage() {
		System.out.println("AddCarController call.");
		return "add_car";
	}

	@SneakyThrows
	@PostMapping("/add-car.html")
	public String addNewCar(@RequestParam("photo") MultipartFile file, CarTable c) {
		System.out.println(c);
		carService.addNewCarToApp(c, file.getBytes());
		return "redirect:/car-table.html";
	}
}
