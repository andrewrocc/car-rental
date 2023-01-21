package infrastructure.service;

import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.model.CarPhoto;
import infrastructure.repository.CarBrandRepository;
import infrastructure.repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class AddCarService {

    private final CarService carService;

    private final CarPhotoService photoService;

    private final CarModelRepository modelRepository;

    private final CarBrandRepository brandRepository;

    public void addNewCarToApp(CarInfoDTO carInfoDTO, byte[] photo) {
        CarModel carModel = modelRepository.findByName(carInfoDTO.getModel());
        CarBrand carBrand = brandRepository.findByName(carInfoDTO.getBrand());
        Car car = new Car();

        if (carBrand == null) {
            carBrand = new CarBrand();
            carBrand.setBrandName(carInfoDTO.getBrand());
            carBrand = brandRepository.saveAndFlush(carBrand);
        }

        if (carModel == null) {
            carModel = new CarModel();
            carModel.setCarBrand(carBrand);
            carModel.setModelName(carInfoDTO.getModel());
            carModel = modelRepository.saveAndFlush(carModel);
        }

        car.setNumberCar(carInfoDTO.getNumber());
        car.setPrice(new BigDecimal(carInfoDTO.getPrice()));
        car.setCarBrand(carBrand);
        car.setCarModel(carModel);
        car = carService.add(car);

        if (car.getPhoto() == null) {
            CarPhoto carPhoto = new CarPhoto();
            carPhoto.setCar(car);
            carPhoto.setPhoto(photo);
            photoService.addNewCarPhoto(carPhoto);
        }
    }
}
