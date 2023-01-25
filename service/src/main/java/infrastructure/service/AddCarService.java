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

    public void add(CarInfoDTO carInfoDTO, byte[] photo) {
        CarModel carModel = modelRepository.findByName(carInfoDTO.getCarModel());
        CarBrand carBrand = brandRepository.findByName(carInfoDTO.getCarBrand());

        if (carBrand == null) {
            carBrand = new CarBrand();
            carBrand.setBrandName(carInfoDTO.getCarBrand());
            carBrand = brandRepository.saveAndFlush(carBrand);
        }

        if (carModel == null) {
            carModel = new CarModel();
            carModel.setCarBrand(carBrand);
            carModel.setModelName(carInfoDTO.getCarModel());
            carModel = modelRepository.saveAndFlush(carModel);
        }

        Car car = Car.builder().number(carInfoDTO.getNumber()).price(new BigDecimal(carInfoDTO.getPrice()))
                .carBrand(carBrand).carModel(carModel).build();
        car = carService.add(car);

        if (car.getPhoto() == null) {
            CarPhoto carPhoto = new CarPhoto();
            carPhoto.setCar(car);
            carPhoto.setPhoto(photo);
            photoService.addNewCarPhoto(carPhoto);
        }
    }
}
