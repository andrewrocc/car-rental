package infrastructure.service;

import infrastructure.model.CarPhoto;

public interface CarPhotoService {

    void add(CarPhoto p);

    CarPhoto getByCarId(long id);

    void update(CarPhoto c);
}
