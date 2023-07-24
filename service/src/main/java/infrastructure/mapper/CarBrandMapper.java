package infrastructure.mapper;

import infrastructure.dto.CarBrandDTO;
import infrastructure.model.CarBrand;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class)
public abstract class CarBrandMapper {

    public abstract CarBrandDTO mapToDto(CarBrand carBrand);
}
