package infrastructure.repository;

import infrastructure.model.CarPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarPhotoRepository extends JpaRepository<CarPhoto, String> {

    @Query(value = "SELECT * FROM CAR_PHOTO c WHERE c.CAR_ID=:id", nativeQuery = true)
    CarPhoto findCarPhotoByCarId(@Param("id") long id);
}
