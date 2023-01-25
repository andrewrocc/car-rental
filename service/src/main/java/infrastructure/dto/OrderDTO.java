package infrastructure.dto;

import infrastructure.model.Order;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private long id;

    private String price;

    private String date;

    private String numberOfDay;

    private String brand;

    private String model;

    private String login;

    public static OrderDTO from(Order order) {
        return getBuilder(order).build();
    }

    private static OrderDTOBuilder getBuilder(Order order) {
        String brand = order.getCars().size() == 0 ? "The brand has been deleted" : order.getAllCars()[0].getCarBrand().getBrandName();
        String model = order.getCars().size() == 0 ? "The model has been deleted" : order.getAllCars()[0].getCarModel().getModelName();
        String user = order.getAllUsers().length == 0 ? "The user has been deleted" : order.getAllUsers()[0].getEmail();

        return OrderDTO.builder().id(order.getId()).price(String.valueOf(order.getPrice()))
                .date(order.getDate().toString()).numberOfDay(String.valueOf(order.getNumberOfDay()))
                .brand(brand).model(model)
                .login(user);
    }
}
