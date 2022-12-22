package infrastructure.repository;

import infrastructure.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    @Query(value = "SELECT * FROM CAR_MODEL c WHERE c.NAME LIKE %:name%", nativeQuery = true)
    List<CarModel> findAllCarModelByName(String name);
}
