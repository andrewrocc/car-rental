package infrastructure.dao.impl;

import infrastructure.dao.PaymentDao;
import infrastructure.dao.base.BaseDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.Payment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PaymentDaoImpl extends BaseDaoImpl<Payment> implements PaymentDao {

	public PaymentDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Payment.class);
	}

}
