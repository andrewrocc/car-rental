package infrastructure.dto;

import infrastructure.util.Constant;
import infrastructure.model.Order;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDto {

    private long id;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String firstName;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String lastName;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String email;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    @Size(message = Constant.NOT_DIGITS_MESSAGE, min = 16, max = 16)
    private String paymentCard;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String password;

    private boolean isAdmin;

    private Order[] orders;

    private OrderDTO[] orderDTOs;
}
