package infrastructure.repository;

import infrastructure.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM CAR c WHERE c.MODEL_ID=:id", nativeQuery = true)
    Car findCarByModelId(@Param("id") long id);

    @Query(value = "SELECT * FROM CAR c WHERE c.NUMBER=:number", nativeQuery = true)
    Car findCarByNumber(@Param("number") String number);

    @Query(value = """
            SELECT * FROM CAR c
            INNER JOIN CAR_BRAND b ON c.BRAND_ID=b.ID
            INNER JOIN CAR_MODEL m ON c.MODEL_ID=m.ID
            WHERE b.NAME LIKE %:keyword%""", nativeQuery = true)
    List<Car> findCarsByBrandName(@Param("keyword") String keyword);

    @Query(value = """
            SELECT * FROM CAR c
            INNER JOIN CAR_BRAND b ON c.BRAND_ID=b.ID
            INNER JOIN CAR_MODEL m ON c.MODEL_ID=m.ID
            WHERE m.NAME LIKE %:keyword%""", nativeQuery = true)
    List<Car> findCarsByModelName(@Param("keyword") String keyword);
}
