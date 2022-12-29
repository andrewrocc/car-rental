package infrastructure.service;

import infrastructure.dto.CarTable;
import infrastructure.model.Car;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.repository.CarBrandRepository;
import infrastructure.repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AddCarService {

    private final CarService carService;

    private final CarModelRepository modelRepository;

    private final CarBrandRepository brandRepository;

    public void addNewCarToApp(CarTable c) {
        CarModel carModel = modelRepository.findByName(c.getModel());
        CarBrand carBrand = brandRepository.findByName(c.getBrand());

        if (carBrand == null) {
            carBrand = new CarBrand();
            carBrand.setBrandName(c.getBrand());
            carBrand = brandRepository.saveAndFlush(carBrand);
        }

        if (carModel == null) {
            carModel = new CarModel();
            carModel.setCarBrand(carBrand);
            carModel.setModelName(c.getModel());
            carModel = modelRepository.saveAndFlush(carModel);
        }

        Car car = new Car();
        car.setNumberCar(c.getNumber());
        car.setPrice(new BigDecimal(c.getPrice()));
        car.setCarBrand(carBrand);
        car.setCarModel(carModel);
        carService.addNewCarToDB(car);
    }
}
