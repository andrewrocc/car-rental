package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.Payment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PaymentDaoImpl implements PaymentDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<Payment> paymentDao;

	public PaymentDaoImpl(SessionFactory sessionFactory) {
		paymentDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(Payment obj) {
		paymentDao.create(obj);
	}

	@Override
	public Payment findById(Class classType, long id) {
		return paymentDao.findById(classType, id);
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
