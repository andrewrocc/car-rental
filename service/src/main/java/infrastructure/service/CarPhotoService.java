package infrastructure.service;

import infrastructure.model.CarPhoto;
import infrastructure.repository.CarPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarPhotoService {

    private final CarPhotoRepository photoRepository;

    public void addNewCarPhoto(CarPhoto p) {
        photoRepository.saveAndFlush(p);
    }

    public CarPhoto getCarPhotoByCarId(long id) {
        CarPhoto photo = photoRepository.findCarPhotoByCarId(id);
        System.out.println("photo size: " + photo.getPhoto().length);
        return photo;
    }

    public void update(CarPhoto c) {
        photoRepository.save(c);
    }
}

