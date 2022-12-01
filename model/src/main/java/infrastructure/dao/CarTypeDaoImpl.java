package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.CarType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CarTypeDaoImpl implements CarTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<CarType> carTypeDao;

	public CarTypeDaoImpl(SessionFactory sessionFactory) {
		carTypeDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(CarType obj) {
		carTypeDao.create(obj);
	}

	@Override
	public CarType findById(Class classType, long id) {
		return carTypeDao.findById(classType, id);
	}

	@Override
	public void update(CarType obj) {
		carTypeDao.update(obj);
	}

	@Override
	public void delete(CarType obj) {
		carTypeDao.delete(obj);
	}
}
