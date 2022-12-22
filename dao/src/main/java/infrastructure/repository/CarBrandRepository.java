package infrastructure.repository;

import infrastructure.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {

    @Query(value = "SELECT * FROM CAR_BRAND c WHERE c.NAME LIKE %:name%", nativeQuery = true)
    List<CarBrand> findAllBrandByName(String name);
}
