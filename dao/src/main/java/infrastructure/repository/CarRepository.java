package infrastructure.repository;

import infrastructure.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM CAR c WHERE c.MODEL_ID=:id", nativeQuery = true)
    Car findCarByModelId(@Param("id") long id);
}
