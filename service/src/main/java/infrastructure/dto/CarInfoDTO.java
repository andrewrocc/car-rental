package infrastructure.dto;

import com.google.gson.annotations.Expose;
import infrastructure.config.Constant;
import infrastructure.model.Car;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarInfoDTO implements Serializable {

    private long id;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String model;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String brand;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    @Size(message = Constant.NOT_DIGITS_MESSAGE, min = 4, max = 10)
    private String number;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    @Digits(message = Constant.NOT_DIGITS_MESSAGE, integer = 6, fraction = 2)
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
