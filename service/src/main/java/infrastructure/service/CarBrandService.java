package infrastructure.service;

import infrastructure.model.CarBrand;
import infrastructure.repository.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarBrandService {

	@Autowired
	private CarBrandRepository carBrandRepository;

	private List<CarBrand> carBrands;

	@Transactional
	public List<CarBrand> getListCarBrand() {
		if (carBrands == null || carBrands.isEmpty()) {
			return carBrands = carBrandRepository.findAll();
		} else {
			return carBrands;
		}
	}
}
