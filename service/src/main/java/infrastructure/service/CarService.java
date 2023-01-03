package infrastructure.service;

import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

	private final CarRepository carRepository;

	public Car addNewCar(Car c) {
		return carRepository.saveAndFlush(c);
	}

	public Car getCarById(long id) {
		return carRepository.findById(id).get();
	}

	public void deleteCar(long id) {
		carRepository.deleteById(id);
	}

	public Car getCarByModelId(long id) {
		return carRepository.findCarByModelId(id);
	}

	public Car getReferenceById(long id) {
	 	return carRepository.getReferenceById(id);
	}

	public List<CarInfoDTO> getCarTable() {
		List<Car> cars = carRepository.findAll();
		List<CarInfoDTO> carInfoDTOS = new ArrayList<>(cars.size());
		for (Car car : cars) {
			CarInfoDTO c = new CarInfoDTO();
			c.setId(car.getId());
			c.setModel(car.getCarModel().getModelName());
			c.setBrand(car.getCarBrand().getBrandName());
			c.setNumber(car.getNumberCar());
			c.setPrice(car.getPrice().toString());
			carInfoDTOS.add(c);
		}
		return carInfoDTOS;
	}

	public void update(Car c) {
		carRepository.save(c);
	}
}
