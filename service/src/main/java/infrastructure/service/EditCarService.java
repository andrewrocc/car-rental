package infrastructure.service;

import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.model.CarPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class EditCarService {

    private final CarService carService;

    private final CarBrandService brandService;

    private final CarModelService modelService;

    private final CarPhotoService photoService;

    public void updateCarInfo(long id, CarInfoDTO c) {
        Car car = carService.getReferenceById(id);
        CarBrand carBrand = brandService.findByName(c.getBrand());
        CarModel carModel = modelService.findByName(c.getModel());
        carBrand = setCarBrandInCar(car, carBrand, c);
        setCarModelInCar(car, carModel, carBrand, c);
        setCarNumber(car, c);
        setCarPrice(car, c);
        carService.update(car);
    }

    public void updateCarPhoto(long id, byte[] photo) {
        Car car = carService.getReferenceById(id);
        if (car.getPhoto() == null) {
            CarPhoto carPhoto = new CarPhoto();
            carPhoto.setCar(car);
            carPhoto.setPhoto(photo);
            photoService.update(carPhoto);
        } else {
            CarPhoto carPhoto = photoService.getCarPhotoByCarId(id);
            carPhoto.setCar(car);
            carPhoto.setPhoto(photo);
            photoService.update(carPhoto);
        }
    }

    private CarBrand setCarBrandInCar(Car car, CarBrand carBrand, CarInfoDTO c) {
        boolean isEquals = car.getCarBrand().getBrandName().equalsIgnoreCase(c.getBrand());
        if (!isEquals) {
            if (carBrand == null) {
                carBrand = new CarBrand();
                carBrand.setBrandName(c.getBrand());
                brandService.update(carBrand);
            }
            car.setCarBrand(carBrand);
        }
        return carBrand;
    }

    private void setCarModelInCar(Car car, CarModel carModel, CarBrand carBrand, CarInfoDTO c) {
        boolean isEquals = car.getCarModel().getModelName().equalsIgnoreCase(c.getModel());
        if (!isEquals) {
            if (carModel == null) {
                carModel = new CarModel();
                carModel.setModelName(c.getModel());
                carModel.setCarBrand(carBrand);
                modelService.update(carModel);
            }
            car.setCarModel(carModel);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private void setCarPrice(Car car, CarInfoDTO c) {
        boolean isEquals = car.getPrice().toString().equalsIgnoreCase(c.getPrice());
        if (!isEquals) {
            car.setPrice(new BigDecimal(c.getPrice()));
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private void setCarNumber(Car car, CarInfoDTO c) {
        boolean isEquals = car.getNumberCar().equalsIgnoreCase(c.getNumber());
        if (!isEquals) {
            car.setNumberCar(c.getNumber());
        }
    }
}
