package infrastructure.service;

import infrastructure.model.CarBrand;
import infrastructure.repository.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CarBrandService {

	@Autowired
	private CarBrandRepository carBrandRepository;

	public CarBrand findByName(String name) {
		return carBrandRepository.findByName(name);
	}

	public void update(CarBrand carBrand) {
		carBrandRepository.save(carBrand);
	}

	public CarBrand findById(Long id) {
		if (carBrandRepository.findById(id).isPresent())
			return carBrandRepository.findById(id).get();
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
