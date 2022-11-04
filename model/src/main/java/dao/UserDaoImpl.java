package dao;

import db.connection.properties.MysqlSessionFactory;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

	private final SessionFactory sessionFactory;

	private BaseDaoImpl<User> userDao;

	public UserDaoImpl() {
		this(MysqlSessionFactory.getInstance());
		userDao = new BaseDaoImpl<>(User.class);
	}

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		userDao = new BaseDaoImpl<>(sessionFactory, User.class);
	}

	@Override
	public void create(User obj) {
		userDao.create(obj);
	}

	@Override
	public User findById(long id) {
		return userDao.findById(id);
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
