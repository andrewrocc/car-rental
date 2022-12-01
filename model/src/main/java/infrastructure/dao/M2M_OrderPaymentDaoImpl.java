package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.M2M_OrderPayment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class M2M_OrderPaymentDaoImpl implements M2M_OrderPaymentDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<M2M_OrderPayment> orderPaymentBaseDao;

	public M2M_OrderPaymentDaoImpl(SessionFactory sessionFactory) {
		orderPaymentBaseDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(M2M_OrderPayment obj) {
		orderPaymentBaseDao.create(obj);
	}

	@Override
	public M2M_OrderPayment findById(Class classType, long id) {
		return orderPaymentBaseDao.findById(classType, id);
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
