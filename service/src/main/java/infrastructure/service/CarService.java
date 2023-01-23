package infrastructure.service;

import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {

	private final CarRepository carRepository;

	private final ModelMapper modelMapper;

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

	public List<CarInfoDTO> getCarByBrandOrModelName(String keyword) {
		List<Car> brandName = carRepository.findCarsByBrandName(keyword);
		if (brandName.size() == 0) {
			return getCarDTOFromListCar(carRepository.findCarsByModelName(keyword));
		}
		return getCarDTOFromListCar(brandName);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private List<CarInfoDTO> getCarDTOFromListCar(List<Car> cars) {
		return cars.stream().map(car -> modelMapper.map(car, CarInfoDTO.class))
				.collect(Collectors.toCollection(() -> new ArrayList<>(cars.size())));
	}

	public List<CarInfoDTO> getCarTable() {
		List<Car> cars = carRepository.findAll();
		return cars.stream().map(CarInfoDTO::from)
				.collect(Collectors.toCollection(() -> new ArrayList<>(cars.size())));
	}

	public void update(Car c) {
		carRepository.save(c);
	}
}
