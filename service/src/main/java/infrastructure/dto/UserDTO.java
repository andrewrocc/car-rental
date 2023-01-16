package infrastructure.dto;

import infrastructure.model.Order;
import infrastructure.model.User;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String paymentCard;

    private String password;

    private boolean isAdmin;

    private Order[] orders;             // TODO to List is the best

    public static UserDTO from(User user) {
        return getBuilder(user).build();
    }

    public static UserDTO from(User user, boolean isAdmin) {
        return getBuilder(user).isAdmin(isAdmin).build();
    }

    public static UserDTO from(User user, Order[] orders) {
        return getBuilder(user).orders(orders).build();
    }

    public static UserDTO from(User user, boolean isAdmin, Order[] orders) {
        return getBuilder(user).isAdmin(isAdmin).orders(orders).build();
    }

    private static UserDTOBuilder getBuilder(User user) {
        return UserDTO.builder().id(user.getId()).firstName(user.getFirstName())
                .lastName(user.getLastName()).email(user.getEmail())
                .paymentCard(user.getPaymentCard()).password(user.getPassword());
    }
}
