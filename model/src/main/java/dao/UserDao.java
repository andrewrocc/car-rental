package dao;

import models.User;

public interface UserDao {

	void create(User user);

	User findById(long id);

	void update(User user);

	void delete(User user);
}
