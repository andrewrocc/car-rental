package dao;

import dao.base.BaseDaoImpl;
import db.connection.properties.MysqlSessionFactory;
import models.M2M_OrderPayment;
import org.hibernate.SessionFactory;

public class M2M_OrderPaymentDaoImpl implements M2M_OrderPaymentDao {

	private final BaseDaoImpl<M2M_OrderPayment> orderPaymentBaseDao;

	public M2M_OrderPaymentDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public M2M_OrderPaymentDaoImpl(SessionFactory sessionFactory) {
		orderPaymentBaseDao = new BaseDaoImpl<>(sessionFactory, M2M_OrderPayment.class);
	}

	@Override
	public void create(M2M_OrderPayment obj) {
		orderPaymentBaseDao.create(obj);
	}

	@Override
	public M2M_OrderPayment findById(long id) {
		return orderPaymentBaseDao.findById(id);
	}

	@Override
	public void update(M2M_OrderPayment obj) {
		orderPaymentBaseDao.update(obj);
	}

	@Override
	public void delete(M2M_OrderPayment obj) {
		orderPaymentBaseDao.delete(obj);
	}
}
