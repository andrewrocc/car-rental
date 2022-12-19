package infrastructure.dao.base;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
//@RequiredArgsConstructor			// TODO required this shit
abstract public class BaseDaoImpl<T> implements BaseDao<T> {

	protected final SessionFactory sessionFactory;

	private final Class classType;

	public BaseDaoImpl(SessionFactory sessionFactory, Class classType) {
		this.sessionFactory = sessionFactory;
		this.classType = classType;
	}

	@Override
	public void create(T obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
	}

	@Override
	public T findById(long id) {
		return (T) sessionFactory.getCurrentSession().get(classType, id);
	}

	@Override
	public void update(T obj) {
		create(obj);
	}

	@Override
	public void delete(T obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public List<T> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from " + classType.getName()).list();
	}
}
