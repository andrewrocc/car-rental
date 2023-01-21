package infrastructure.dto;

import infrastructure.config.Constant;
import infrastructure.model.Order;
import infrastructure.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

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

    public static UserDTO from(User user, boolean isAdmin) {
        return getBuilder(user).isAdmin(isAdmin).build();
    }

    public static UserDTO from(User user, Order[] orders) {
        return getBuilder(user).orders(orders).build();
    }

    private static UserDTOBuilder getBuilder(User user) {
        return UserDTO.builder().id(user.getId()).firstName(user.getFirstName())
                .lastName(user.getLastName()).email(user.getEmail())
                .paymentCard(user.getPaymentCard()).password(user.getPassword());
    }
}
