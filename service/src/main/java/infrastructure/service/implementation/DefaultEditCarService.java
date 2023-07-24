package infrastructure.service.implementation;

import infrastructure.dto.CarDto;
import infrastructure.model.Car;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.model.CarPhoto;
import infrastructure.service.CarBrandService;
import infrastructure.service.CarModelService;
import infrastructure.service.CarPhotoService;
import infrastructure.service.CarService;
import infrastructure.service.EditCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultEditCarService implements EditCarService {

    private final CarService carService;

    private final CarBrandService brandService;

    private final CarModelService modelService;

    private final CarPhotoService photoService;

    @Override
    public Car updateInfo(long id, CarDto carDto) {
        Car car = carService.getReferenceById(id);
        CarBrand carBrand = brandService.findByName(carDto.getCarBrand());
        CarModel carModel = modelService.findByName(carDto.getCarModel());
        carBrand = setCarBrandInCar(car, carBrand, carDto);
        setCarModelInCar(car, carModel, carBrand, carDto);
        setCarNumber(car, carDto);
        setCarPrice(car, carDto);
        return carService.update(car);
    }

    @Override
    public void updatePhoto(long id, byte[] photo) {
        Car car = carService.getReferenceById(id);
        if (Objects.isNull(car.getPhoto())) {
            CarPhoto carPhoto = new CarPhoto().setCar(car).setPhoto(photo);
            photoService.update(carPhoto);
        } else {
            CarPhoto carPhoto = photoService.getByCarId(id).setCar(car).setPhoto(photo);
            photoService.update(carPhoto);
        }
    }

    private CarBrand setCarBrandInCar(Car car, CarBrand carBrand, CarDto carDto) {
        if (!car.getCarBrand().getBrandName().equalsIgnoreCase(carDto.getCarBrand())) {
            if (Objects.isNull(carBrand)) {
                carBrand = new CarBrand().setBrandName(carDto.getCarBrand());
                brandService.update(carBrand);
            }
            car.setCarBrand(carBrand);
        }
        return carBrand;
    }

    private void setCarModelInCar(Car car, CarModel carModel, CarBrand carBrand, CarDto carDto) {
        if (!car.getCarModel().getModelName().equalsIgnoreCase(carDto.getCarModel())) {
            if (Objects.isNull(carModel)) {
                carModel = new CarModel()
                        .setModelName(carDto.getCarModel())
                        .setCarBrand(carBrand);
                modelService.update(carModel);
            }
            car.setCarModel(carModel);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private void setCarPrice(Car car, CarDto carDto) {
        if (!car.getPrice().toString().equalsIgnoreCase(carDto.getPrice())) {
            car.setPrice(new BigDecimal(carDto.getPrice()));
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private void setCarNumber(Car car, CarDto carDto) {
        if (!car.getNumber().equalsIgnoreCase(carDto.getNumber())) {
            car.setNumber(carDto.getNumber());
        }
    }
}
