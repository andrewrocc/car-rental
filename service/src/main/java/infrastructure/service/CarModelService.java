package infrastructure.service;

import infrastructure.dto.CarModelDTO;
import infrastructure.model.CarModel;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CarModelService {

    CarModel findByName(String name);

    void update(CarModel carModel);

    void deleteModel(long id);

    List<CarModelDTO> getAllCarModels(PageRequest pageRequest);
}
