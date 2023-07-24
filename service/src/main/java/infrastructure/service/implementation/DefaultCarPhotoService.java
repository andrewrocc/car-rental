package infrastructure.service.implementation;

import infrastructure.model.CarPhoto;
import infrastructure.repository.CarPhotoRepository;
import infrastructure.service.CarPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCarPhotoService implements CarPhotoService {

    private final CarPhotoRepository photoRepository;

    @Override
    public void add(CarPhoto p) {
        photoRepository.saveAndFlush(p);
    }

    @Override
    public CarPhoto getByCarId(long id) {
        CarPhoto photo = photoRepository.findByCarId(id);
        log.info("photo size: " + photo.getPhoto().length);
        return photo;
    }

    @Override
    public void update(CarPhoto c) {
        photoRepository.save(c);
    }
}

