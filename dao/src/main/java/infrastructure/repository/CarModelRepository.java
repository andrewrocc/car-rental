package infrastructure.repository;

import infrastructure.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    @Query(value = "SELECT * FROM CAR_MODEL c WHERE c.NAME LIKE %:name%", nativeQuery = true)
    List<CarModel> findAllCarModelByName(@Param("name") String name);

    @Query(value = "SELECT * FROM CAR_MODEL c WHERE c.NAME=:name", nativeQuery = true)
    CarModel findByName(@Param("name") String name);

    @Query(value = "SELECT * FROM CAR_MODEL c WHERE c.BRAND_ID=:id", nativeQuery = true)
    CarModel findByBrandId(@Param("id") long id);
}
