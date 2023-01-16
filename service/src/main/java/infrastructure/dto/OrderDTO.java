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
        return OrderDTO.builder().id(order.getId()).price(String.valueOf(order.getPrice()))
                .date(order.getDate().toString()).numberOfDay(String.valueOf(order.getNumberOfDay()))
                .brand(order.getAllCars()[0].getCarBrand().getBrandName())
                .model(order.getAllCars()[0].getCarModel().getModelName())
                .login(order.getAllUsers()[0].getEmail());
    }
}
