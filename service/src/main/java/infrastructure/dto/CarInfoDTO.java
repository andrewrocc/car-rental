package infrastructure.dto;

import infrastructure.config.Constant;
import infrastructure.model.Car;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarInfoDTO implements Serializable {

    private long id;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String carModel;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String carBrand;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    @Size(message = Constant.NOT_DIGITS_MESSAGE, min = 4, max = 10)
    private String number;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    @Digits(message = Constant.NOT_DIGITS_MESSAGE, integer = 6, fraction = 2)
    private String price;

    private byte[] photo;

    public static CarInfoDTO from(Car car) {
        return getBuilder(car).photo(car.getPhoto() == null ? null : car.getPhoto().getPhoto()).build();
    }

    public static CarInfoDTO from_withoutPhoto(Car car) {
        return getBuilder(car).build();
    }

    private static CarInfoDTOBuilder getBuilder(Car car) {
        return CarInfoDTO.builder().id(car.getId()).carModel(car.getCarModel().getModelName())
                .carBrand(car.getCarBrand().getBrandName()).number(car.getNumber())
                .price(String.valueOf(car.getPrice()));
    }
}