package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<Order> orderDao;

	public OrderDaoImpl(SessionFactory sessionFactory) {
		orderDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(Order obj) {
		orderDao.create(obj);
	}

	@Override
	public Order findById(Class classType, long id) {
		return orderDao.findById(classType, id);
	}

	@Override
	public void update(Order obj) {
		orderDao.update(obj);
	}

	@Override
	public void delete(Order obj) {
		orderDao.delete(obj);
	}
}
