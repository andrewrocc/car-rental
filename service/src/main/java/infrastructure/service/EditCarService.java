package infrastructure.service;

import infrastructure.dto.CarDto;
import infrastructure.model.Car;

public interface EditCarService {

    Car updateInfo(long id, CarDto carDto);

    void updatePhoto(long id, byte[] photo);
}
