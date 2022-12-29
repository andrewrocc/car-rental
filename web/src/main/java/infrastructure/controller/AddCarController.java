package infrastructure.controller;

import infrastructure.dto.CarTable;
import infrastructure.service.AddCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AddCarController {

	private final AddCarService carService;

	@GetMapping("/add-car.html")
	public String getAddNewCarPage() {
		System.out.println("AddCarController call.");
		return "add_car";
	}

	@PostMapping("/add-car.html")
	public String addNewCar(CarTable c) {
		System.out.println(c);
		carService.addNewCarToApp(c);
//		carService.addNewCar(c);
		return "redirect:/car-table.html";
	}
}
