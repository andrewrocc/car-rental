package infrastructure.service;

import infrastructure.model.Car;
import infrastructure.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {


	@Autowired
	private CarRepository carRepository;

	@Transactional
	public void addNewCar(Car c) {
		carRepository.saveAndFlush(c);
	}
}
