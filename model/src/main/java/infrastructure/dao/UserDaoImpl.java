package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<User> userDao;

	public UserDaoImpl(SessionFactory sessionFactory) {
		userDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(User obj) {
		userDao.create(obj);
	}

	@Override
	public User findById(Class classType, long id) {
		return userDao.findById(classType, id);
	}

	@Override
	public void update(User obj) {
		create(obj);
	}

	@Override
	public void delete(User obj) {
		userDao.delete(obj);
	}

	@Override
	public List<User> getAllUsers() {
		String query = "SELECT * FROM t_users;";
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery(query, User.class).list();
		}
	}
}
