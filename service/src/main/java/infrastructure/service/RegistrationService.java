package infrastructure.service;

import infrastructure.dao.UserDao;
import infrastructure.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserDao userDao;

    public void addNewUser(User u) {
        userDao.create(u);
    }
}
