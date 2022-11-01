package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import db.connection.properties.MysqlSessionFactory;

public class UserDaoImpl implements UserDao {

	private final SessionFactory sessionFactory;

	public UserDaoImpl() throws ClassNotFoundException {
		sessionFactory = MysqlSessionFactory.getInstance();
	}

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(User user) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) transaction.rollback();
			throw ex;
		}
	}

	@Override
	public User findById(long id) {
		Session session = sessionFactory.openSession();
		User user = session.get(User.class, id);
		session.close();
		return user;
	}

	@Override
	public void update(User user) {
		create(user);
	}

	@Override
	public void delete(User user) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) transaction.rollback();
			throw ex;
		}
	}
}
