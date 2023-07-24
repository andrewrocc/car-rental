package infrastructure.repository;

import infrastructure.model.CarPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPhotoRepository extends JpaRepository<CarPhoto, String> {

    CarPhoto findByCarId(long id);
}
