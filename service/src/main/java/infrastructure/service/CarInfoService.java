package infrastructure.service;

import infrastructure.dto.CarTable;
import infrastructure.model.Car;
import infrastructure.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarInfoService {

    private final CarRepository carRepository;

    @Transactional
    public CarTable getCarInfoById(Long id) {
        Car car = carRepository.getReferenceById(id);
        CarTable carTable = new CarTable();
        carTable.setId(car.getId());
        carTable.setModel(car.getCarModel().getModelName());
        carTable.setBrand(car.getCarBrand().getBrandName());
        carTable.setNumber(car.getNumberCar());
        carTable.setPrice(car.getPrice().toString());
        return carTable;
    }
}
