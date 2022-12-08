package infrastructure.controller;

import infrastructure.models.Car;
import infrastructure.models.CarBrand;
import infrastructure.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AddCarController {

	@Autowired
	private CarBrandService carBrandService;

	@GetMapping("/add-car.html")
	public ModelAndView getAddNewCarPage() {
		List<String> brands = carBrandService.getListCarBrand();
		ModelAndView view = new ModelAndView();
		view.addObject("listBrands", brands);
		view.setViewName("add_car");
		return view;
	}

	@PostMapping("/add-car.html")
	public String addNewCar(Car c) {
		System.out.println(c);
//		carService.addNewCar(c);
		return "redirect:/index.html";
	}
}
