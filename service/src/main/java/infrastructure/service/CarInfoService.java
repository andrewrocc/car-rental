package infrastructure.service;

import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.model.CarPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarInfoService {

    private final CarService carService;

    private final CarPhotoService photoService;

    public CarInfoDTO getCarInfoById(Long id) {
        Car car = carService.getReferenceById(id);
        CarInfoDTO carInfoDTO = CarInfoDTO.from(car);
        if (car.getPhoto() != null) {
            CarPhoto carPhoto = photoService.getCarPhotoByCarId(id);
            carInfoDTO.setPhoto(carPhoto.getPhoto());
        }
        return carInfoDTO;
    }
}
