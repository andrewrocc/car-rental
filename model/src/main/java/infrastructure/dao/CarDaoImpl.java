package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CarDaoImpl implements CarDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<Car> carDao;

	@Autowired
	public CarDaoImpl(SessionFactory sessionFactory) {
		carDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(Car obj) {
		carDao.create(obj);
	}

	@Override
	public Car findById(Class classType, long id) {
		return carDao.findById(classType, id);
	}

	@Override
	public void update(Car obj) {
		carDao.update(obj);
	}

	@Override
	public void delete(Car obj) {
		carDao.delete(obj);
	}
}
