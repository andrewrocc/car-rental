package infrastructure.dto;

import infrastructure.model.Car;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarInfoDTO {

    private final String NOT_BLANK_MESSAGE = "This field cannot be empty.";

    private final String NOT_DIGITS_MESSAGE = "Only digits are accepted.";

    private long id;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String model;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String brand;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(message = NOT_DIGITS_MESSAGE, min = 4, max = 10)
    private String number;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Digits(message = NOT_DIGITS_MESSAGE, integer = 6, fraction = 2)
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
