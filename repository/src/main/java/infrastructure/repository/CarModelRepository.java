package infrastructure.repository;

import infrastructure.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    CarModel findByModelName(String modelName);
}
