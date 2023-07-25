package infrastructure.mapper;

import infrastructure.dto.OrderDto;
import infrastructure.model.Order;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfiguration.class)
public abstract class OrderMapper {

    public OrderDto mapToDto(Order order) {
        String brand = order.getCars().size() == 0 ? "The brand has been deleted" : order.getAllCars()[0].getCarBrand().getBrandName();
        String model = order.getCars().size() == 0 ? "The model has been deleted" : order.getAllCars()[0].getCarModel().getModelName();
        String user = order.getAllUsers().length == 0 ? "The user has been deleted" : order.getAllUsers()[0].getEmail();

        return new OrderDto().setId(order.getId())
                .setPrice(String.valueOf(order.getPrice()))
                .setDate(order.getDate().toString())
                .setNumberOfDay(String.valueOf(order.getNumberOfDay()))
                .setBrand(brand)
                .setModel(model)
                .setLogin(user);
    }
}
