package infrastructure.service.implementation;

import infrastructure.dto.CarDto;
import infrastructure.exception.WebServiceException;
import infrastructure.mapper.CarMapper;
import infrastructure.model.Car;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.model.CarPhoto;
import infrastructure.model.Order;
import infrastructure.repository.CarBrandRepository;
import infrastructure.repository.CarModelRepository;
import infrastructure.repository.CarRepository;
import infrastructure.service.CarModelService;
import infrastructure.service.CarPhotoService;
import infrastructure.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCarService implements CarService {

	private final CarRepository carRepository;

	private final CarMapper carMapper;

	private final CarPhotoService photoService;

	private final CarModelRepository modelRepository;

	private final CarBrandRepository brandRepository;

	private final CarModelService carModelService;

	@Override
	public void add(CarDto carDto, byte[] photo) {
		CarModel carModel = modelRepository.findByModelName(carDto.getCarModel());
		CarBrand carBrand = brandRepository.findByName(carDto.getCarBrand());

		if (Objects.isNull(carBrand)) {
			carBrand = new CarBrand().setBrandName(carDto.getCarBrand());
			carBrand = brandRepository.saveAndFlush(carBrand);
		}

		if (Objects.isNull(carModel)) {
			carModel = new CarModel().setCarBrand(carBrand)
					.setModelName(carDto.getCarModel());
			carModel = modelRepository.saveAndFlush(carModel);
		}

		Car car = new Car().setNumber(carDto.getNumber())
				.setPrice(new BigDecimal(carDto.getPrice()))
				.setCarBrand(carBrand)
				.setCarModel(carModel);
		car = carRepository.saveAndFlush(car);

		if (Objects.isNull(car.getPhoto())) {
			CarPhoto carPhoto = new CarPhoto().setCar(car)
					.setPhoto(photo);
			photoService.add(carPhoto);
		}
	}

	@Override
	public Car add(Car c) {
		return carRepository.saveAndFlush(c);
	}

	@Override
	public Car getById(long id) {
		Optional<Car> carResponse = carRepository.findById(id);
		if (carResponse.isPresent())
			return carResponse.get();
		throw new WebServiceException("car with id " + id + " do not found", HttpStatus.NOT_FOUND);
	}

	@Override
	public CarDto getByIdAndPhoto(Long id) {
		Car car = getById(id);
		CarDto carDto = carMapper.mapWithPhotoToDto(getById(id));
		if (car.getPhoto() != null) {
			CarPhoto carPhoto = photoService.getByCarId(id);
			carDto.setPhoto(carPhoto.getPhoto());
		}
		return carDto;
	}

	@Override
	public void deleteFromRepository(long id) {
		carRepository.deleteById(id);
	}

	@Override
	public void deleteById(long id) {
		Car car = getById(id);
		Order[] orders = car.getAllCars();
		long carModelId = car.getCarModel().getId();
		for (int i = 0; i < orders.length; i++) {
			car.removeOrder(orders[i]);
		}
		deleteFromRepository(id);
		if (Objects.isNull(getByModelId(carModelId))) {
			carModelService.deleteModel(carModelId);
		}
	}

	@Override
	public Car getByModelId(long id) {
		return carRepository.findCarByModelId(id);
	}

	@Override
	public Car getReferenceById(long id) {
		return carRepository.getReferenceById(id);
	}


	@Override
	public List<CarDto> getAll(PageRequest pageRequest) {
		List<Car> cars = carRepository.findAll(pageRequest).getContent();
		return cars.stream().map(carMapper::mapToDto)
				.collect(Collectors.toCollection(() -> new ArrayList<>(cars.size())));
	}

	@Override
	public List<CarDto> getByBrandOrModelName(String keyword) {
		return carRepository.findCarsByBrandName(keyword).stream().map(carMapper::mapToDto)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public Car update(Car c) {
		return carRepository.save(c);
	}
}
