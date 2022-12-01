package infrastructure.dao.base;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	public BaseDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void create(T obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
	}

	@Override
	public T findById(Class classType, long id) {
		return (T) sessionFactory.getCurrentSession().get(classType, id);
	}

//  TODO: Is this method necessary?
//	@Override
//	public List<T> getAllElements(String query) {
//		try (Session session = sessionFactory.openSession()) {
//			return (List<T>) session.createQuery(query, String.class).list();
//		}
//	}

	@Override
	public void update(T obj) {
		create(obj);
	}

	@Override
	public void delete(T obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}
}
