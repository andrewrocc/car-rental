package infrastructure.dao;

import infrastructure.dao.base.BaseDao;
import infrastructure.models.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {

	List<User> getAllUsers();
}
