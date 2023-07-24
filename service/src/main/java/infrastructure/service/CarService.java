package infrastructure.service;

import infrastructure.dto.CarDto;
import infrastructure.model.Car;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CarService {

    void deleteFromRepository(long id);

    void deleteById(long id);

    void add(CarDto carDto, byte[] photo);

    Car add(Car c);

    Car update(Car c);

    Car getById(long id);

    Car getByModelId(long id);

    Car getReferenceById(long id);

    CarDto getByIdAndPhoto(Long id);

    List<CarDto> getAll(PageRequest pageRequest);

    List<CarDto> getByBrandOrModelName(String keyword);
}
