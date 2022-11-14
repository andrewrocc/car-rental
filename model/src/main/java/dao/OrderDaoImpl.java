package dao;

import dao.base.BaseDaoImpl;
import db.connection.properties.MysqlSessionFactory;
import models.Order;
import org.hibernate.SessionFactory;

public class OrderDaoImpl implements OrderDao {

	private final BaseDaoImpl<Order> orderDao;

	public OrderDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public OrderDaoImpl(SessionFactory sessionFactory) {
		orderDao = new BaseDaoImpl<>(sessionFactory, Order.class);
	}

	@Override
	public void create(Order obj) {
		orderDao.create(obj);
	}

	@Override
	public Order findById(long id) {
		return orderDao.findById(id);
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
