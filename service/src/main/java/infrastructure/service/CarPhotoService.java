package infrastructure.service;

import infrastructure.model.CarPhoto;
import infrastructure.repository.CarPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarPhotoService {

    private final CarPhotoRepository photoRepository;

    @Transactional
    public void addNewCarPhoto(CarPhoto p) {
        photoRepository.saveAndFlush(p);
    }
}

