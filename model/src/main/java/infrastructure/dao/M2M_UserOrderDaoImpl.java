package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.M2M_UserOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class M2M_UserOrderDaoImpl implements M2M_UserOrderDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<M2M_UserOrder> userOrderDao;

	public M2M_UserOrderDaoImpl(SessionFactory sessionFactory) {
		userOrderDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(M2M_UserOrder obj) {
		userOrderDao.create(obj);
	}

	@Override
	public M2M_UserOrder findById(Class classType, long id) {
		return userOrderDao.findById(classType, id);
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
