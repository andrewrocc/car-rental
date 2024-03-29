package infrastructure.dto;

import infrastructure.util.Constant;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {

    private long id;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String firstName;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String lastName;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String email;

    @NotBlank(message = Constant.NOT_BLANK_MESSAGE)
    private String password;
}
