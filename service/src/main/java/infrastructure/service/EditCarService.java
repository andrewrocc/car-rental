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

    public Car updateCarInfo(long id, CarInfoDTO carInfoDTO) {
        Car car = carService.getReferenceById(id);
        CarBrand carBrand = brandService.findByName(carInfoDTO.getCarBrand());
        CarModel carModel = modelService.findByName(carInfoDTO.getCarModel());
        carBrand = setCarBrandInCar(car, carBrand, carInfoDTO);
        setCarModelInCar(car, carModel, carBrand, carInfoDTO);
        setCarNumber(car, carInfoDTO);
        setCarPrice(car, carInfoDTO);
        return carService.update(car);
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

    private CarBrand setCarBrandInCar(Car car, CarBrand carBrand, CarInfoDTO carInfoDTO) {
        boolean isEquals = car.getCarBrand().getBrandName().equalsIgnoreCase(carInfoDTO.getCarBrand());
        if (!isEquals) {
            if (carBrand == null) {
                carBrand = new CarBrand();
                carBrand.setBrandName(carInfoDTO.getCarBrand());
                brandService.update(carBrand);
            }
            car.setCarBrand(carBrand);
        }
        return carBrand;
    }

    private void setCarModelInCar(Car car, CarModel carModel, CarBrand carBrand, CarInfoDTO carInfoDTO) {
        boolean isEquals = car.getCarModel().getModelName().equalsIgnoreCase(carInfoDTO.getCarModel());
        if (!isEquals) {
            if (carModel == null) {
                carModel = new CarModel();
                carModel.setModelName(carInfoDTO.getCarModel());
                carModel.setCarBrand(carBrand);
                modelService.update(carModel);
            }
            car.setCarModel(carModel);
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private void setCarPrice(Car car, CarInfoDTO carInfoDTO) {
        boolean isEquals = car.getPrice().toString().equalsIgnoreCase(carInfoDTO.getPrice());
        if (!isEquals) {
            car.setPrice(new BigDecimal(carInfoDTO.getPrice()));
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    private void setCarNumber(Car car, CarInfoDTO carInfoDTO) {
        boolean isEquals = car.getNumber().equalsIgnoreCase(carInfoDTO.getNumber());
        if (!isEquals) {
            car.setNumber(carInfoDTO.getNumber());
        }
    }
}
