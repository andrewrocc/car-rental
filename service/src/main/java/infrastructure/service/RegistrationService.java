package infrastructure.service;

import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    @Transactional
    public void addNewUser(User u) {
        userRepository.saveAndFlush(u);
    }
}
