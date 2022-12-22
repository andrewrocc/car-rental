package infrastructure.service;

import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

//    @Autowired
//    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    public void addNewUser(User u) {
        userRepository.saveAndFlush(u);
//        userDao.create(u);
    }
}
