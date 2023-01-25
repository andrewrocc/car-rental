package infrastructure.service;

import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.model.User;
import infrastructure.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
		Optional<Car> carResponse = carRepository.findById(id);
		if (carResponse.isPresent())
			return carResponse.get();
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
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

	public List<CarInfoDTO> getAllCars(PageRequest pageRequest) {
		List<Car> cars = carRepository.findAll(pageRequest).stream().toList();
		return cars.stream().map(CarInfoDTO::from_withoutPhoto)
				.collect(Collectors.toCollection(() -> new ArrayList<>(cars.size())));
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

	public Car update(Car c) {
		return carRepository.save(c);
	}
}
