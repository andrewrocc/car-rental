package infrastructure.mapper;

import infrastructure.dto.CarDto;
import infrastructure.model.Car;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper(config = MappingConfiguration.class)
public abstract class CarMapper {

    public CarDto mapToDto(Car car) {
        CarDto dto = new CarDto();
        return dto.setId(car.getId()).setCarModel(car.getCarModel().getModelName())
                .setCarBrand(car.getCarBrand().getBrandName()).setNumber(car.getNumber())
                .setPrice(String.valueOf(car.getPrice()));
    }

    public CarDto mapWithPhotoToDto(Car car) {
        return mapToDto(car).setPhoto(Objects.isNull(car.getPhoto()) ? null : car.getPhoto().getPhoto());
    }
}
