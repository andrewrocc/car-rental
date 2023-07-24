package infrastructure.dto;

import infrastructure.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class CarDto implements Serializable {

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
}