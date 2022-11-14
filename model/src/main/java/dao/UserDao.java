package dao;

import dao.base.BaseDao;
import models.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

	List<User> getAllUsers();
}
