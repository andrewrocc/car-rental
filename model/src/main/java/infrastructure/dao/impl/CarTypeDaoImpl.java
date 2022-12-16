package infrastructure.dao.impl;

import infrastructure.dao.CarTypeDao;
import infrastructure.dao.base.BaseDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.CarType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CarTypeDaoImpl extends BaseDaoImpl<CarType> implements CarTypeDao {

	public CarTypeDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, CarType.class);
	}
}
