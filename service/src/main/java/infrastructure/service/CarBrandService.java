package infrastructure.service;

import infrastructure.dto.CarBrandDTO;
import infrastructure.model.CarBrand;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CarBrandService {

    CarBrand findByName(String name);

    void update(CarBrand carBrand);

    List<CarBrandDTO> getAllCarBrands(PageRequest pageRequest);
}
