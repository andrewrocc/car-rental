package infrastructure.dto;

import infrastructure.config.Constant;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

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
