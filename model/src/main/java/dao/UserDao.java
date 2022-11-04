package dao;

import models.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

	List<User> getAllUsers();
}
