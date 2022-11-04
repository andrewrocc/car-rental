package dao;

import models.Payment;
import org.hibernate.SessionFactory;
import db.connection.properties.MysqlSessionFactory;

public class PaymentDaoImpl implements PaymentDao {

	private final BaseDaoImpl<Payment> paymentDao;

	public PaymentDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public PaymentDaoImpl(SessionFactory sessionFactory) {
		paymentDao = new BaseDaoImpl<>(sessionFactory, Payment.class);
	}

	@Override
	public void create(Payment obj) {
		paymentDao.create(obj);
	}

	@Override
	public Payment findById(long id) {
		return paymentDao.findById(id);
	}

	@Override
	public void update(Payment obj) {
		create(obj);
	}

	@Override
	public void delete(Payment obj) {
		paymentDao.delete(obj);
	}
}
