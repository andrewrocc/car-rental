package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.CarBrand;
import infrastructure.models.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	@Override
	public List<CarModel> findAllCarModelByName(String name) {
		String queryFormat = "SELECT * FROM t_cars_model WHERE t_cars_brand.CB_NAME LIKE '%" + name + "%';";
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery(queryFormat, CarModel.class).list();
		}
	}
}
