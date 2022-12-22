package infrastructure.controller;

import infrastructure.model.Car;
import infrastructure.model.CarBrand;
import infrastructure.service.CarBrandService;
import infrastructure.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AddCarController {

	@Autowired
	private CarBrandService carBrandService;

	@Autowired
	private CarModelService carModelService;

	@GetMapping("/add-car.html")
	public ModelAndView getAddNewCarPage() {
		List<CarBrand> brands = carBrandService.getListCarBrand();
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
