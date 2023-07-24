package infrastructure.service;

import infrastructure.dto.UserSignUpDto;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {

    @Transactional
    void registrationNew(UserSignUpDto userSignUpDTO);
}
