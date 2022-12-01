package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.CarModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CarModelDaoImpl implements CarModelDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<CarModel> carModelDao;

	public CarModelDaoImpl(SessionFactory sessionFactory) {
		carModelDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(CarModel obj) {
		carModelDao.create(obj);
	}

	@Override
	public CarModel findById(Class classType, long id) {
		return carModelDao.findById(classType, id);
	}

	@Override
	public void update(CarModel obj) {
		carModelDao.update(obj);
	}

	@Override
	public void delete(CarModel obj) {
		carModelDao.delete(obj);
	}
}
