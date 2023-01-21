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

	public Car add(Car c) {
		return carRepository.saveAndFlush(c);
	}

	public Car getCarById(long id) {
		return carRepository.findById(id).get();
	}

	public void delete(long id) {
		carRepository.deleteById(id);
	}

	public Car getCarByModelId(long id) {
		return carRepository.findCarByModelId(id);
	}

	public Car getReferenceById(long id) {
	 	return carRepository.getReferenceById(id);
	}

	public Car getCarByNumber(String number) {
		return carRepository.findCarByNumber(number);
	}

	public List<CarInfoDTO> getCarTable() {
		List<Car> cars = carRepository.findAll();
		List<CarInfoDTO> carDTO = new ArrayList<>(cars.size());
		for (Car car : cars) {
			CarInfoDTO infoDTO = CarInfoDTO.from(car);
			carDTO.add(infoDTO);
		}
		return carDTO;
	}

	public void update(Car c) {
		carRepository.save(c);
	}
}
