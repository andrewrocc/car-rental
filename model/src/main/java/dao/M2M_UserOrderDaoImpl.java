package dao;

import dao.base.BaseDaoImpl;
import db.connection.properties.MysqlSessionFactory;
import models.M2M_UserOrder;
import org.hibernate.SessionFactory;

public class M2M_UserOrderDaoImpl implements M2M_UserOrderDao {

	private final BaseDaoImpl<M2M_UserOrder> userOrderDao;

	public M2M_UserOrderDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public M2M_UserOrderDaoImpl(SessionFactory sessionFactory) {
		userOrderDao = new BaseDaoImpl<>(sessionFactory, M2M_UserOrder.class);
	}

	@Override
	public void create(M2M_UserOrder obj) {
		userOrderDao.create(obj);
	}

	@Override
	public M2M_UserOrder findById(long id) {
		return userOrderDao.findById(id);
	}

	@Override
	public void update(M2M_UserOrder obj) {
		userOrderDao.update(obj);
	}

	@Override
	public void delete(M2M_UserOrder obj) {
		userOrderDao.delete(obj);
	}
}
