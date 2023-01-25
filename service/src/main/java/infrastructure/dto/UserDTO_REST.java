package infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO_REST {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String paymentCard;

    private String password;

    private boolean isAdmin;
}