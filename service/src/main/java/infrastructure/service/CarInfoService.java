package infrastructure.service;

import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.model.CarPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@RequiredArgsConstructor
public class CarInfoService {

    private final CarService carService;

    private final CarPhotoService photoService;

    public CarInfoDTO getCarInfoById(Long id) {
        Car car = carService.getCarById(id);
        CarInfoDTO carInfoDTO = CarInfoDTO.from(car);
        if (car.getPhoto() != null) {
            CarPhoto carPhoto = photoService.getCarPhotoByCarId(id);
            carInfoDTO.setPhoto(carPhoto.getPhoto());
        }
        return carInfoDTO;
    }

    public CarInfoDTO getCarInfoById_WithoutPhoto(Long id) {
        Car car = carService.getCarById(id);
        return CarInfoDTO.from_withoutPhoto(car);
    }
}
