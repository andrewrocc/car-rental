package infrastructure.dao.impl;

import infrastructure.dao.CarDao;
import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CarDaoImpl extends BaseDaoImpl<Car> implements CarDao {

	@Autowired
	public CarDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, Car.class);
	}
}
