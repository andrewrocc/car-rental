package infrastructure.mapper;

import infrastructure.dto.CarModelDTO;
import infrastructure.model.CarModel;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class)
public abstract class CarModelMapper {

    public abstract CarModelDTO mapToDto(CarModel carModel);
}