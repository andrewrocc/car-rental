package infrastructure.dto;

import infrastructure.model.Car;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarInfoDTO {

    private long id;

    private String model;

    private String brand;

    private String number;

    private String price;

    private byte[] photo;

    public static CarInfoDTO from(Car car) {
        return getBuilder(car).build();
    }

    private static CarInfoDTOBuilder getBuilder(Car car) {
        if (car.getPhoto() == null) {
            return CarInfoDTO.builder().id(car.getId()).model(car.getCarModel().getModelName())
                    .brand(car.getCarBrand().getBrandName()).number(car.getNumberCar())
                    .price(String.valueOf(car.getPrice()));
        }
        return CarInfoDTO.builder().id(car.getId()).model(car.getCarModel().getModelName())
                .brand(car.getCarBrand().getBrandName()).number(car.getNumberCar())
                .price(String.valueOf(car.getPrice())).photo(car.getPhoto().getPhoto());
    }
}
