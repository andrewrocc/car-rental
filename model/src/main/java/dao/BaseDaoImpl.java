package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import db.connection.properties.MysqlSessionFactory;

import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> classType;

	private final SessionFactory sessionFactory;

	public BaseDaoImpl() throws ClassNotFoundException {
		sessionFactory = MysqlSessionFactory.getInstance();
	}

	public BaseDaoImpl(SessionFactory sessionFactory, Class<T> type) {
		this.sessionFactory = sessionFactory;
		this.classType = type;
	}

	@Override
	public void create(T obj) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(obj);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) transaction.rollback();
			throw ex;
		}
	}

	@Override
	public T findById(long id) {
		Session session = sessionFactory.openSession();
		T obj = session.get(classType, id);
		session.close();
		return obj;
	}

	// TODO: Is this method necessary?
	@Override
	public List<T> getAllElements(String query) {
		try (Session session = sessionFactory.openSession()) {
			return (List<T>) session.createQuery(query, String.class).list();
		}
	}

	@Override
	public void update(T obj) {
		create(obj);
	}

	@Override
	public void delete(T obj) {
		Transaction transaction = null;
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			session.delete(obj);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) transaction.rollback();
			throw ex;
		}
	}
}
