package infrastructure.service;

import infrastructure.dto.CarTable;
import infrastructure.model.Car;
import infrastructure.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

	private final CarRepository carRepository;

	@Transactional
	public Car addNewCar(Car c) {
		return carRepository.saveAndFlush(c);
	}

	@Transactional
	public Car getCarById(long id) {
		return carRepository.findById(id).get();
	}

	@Transactional
	public List<CarTable> getCarTable() {
		List<Car> cars = carRepository.findAll();
		List<CarTable> carTables = new ArrayList<>(cars.size());
		for (Car car : cars) {
			CarTable c = new CarTable();
			c.setId(car.getId());
			c.setModel(car.getCarModel().getModelName());
			c.setBrand(car.getCarBrand().getBrandName());
			c.setNumber(car.getNumberCar());
			c.setPrice(car.getPrice().toString());
			carTables.add(c);
		}
		return carTables;
	}
}
