package infrastructure.dao.impl;

import infrastructure.dao.OrderDao;
import infrastructure.dao.base.BaseDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

	public OrderDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Order.class);
	}
}
