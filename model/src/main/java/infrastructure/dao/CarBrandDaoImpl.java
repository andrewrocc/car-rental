package infrastructure.dao;

import infrastructure.dao.base.BaseDaoImpl;
import infrastructure.models.CarBrand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CarBrandDaoImpl implements CarBrandDao {

	@Autowired
	private SessionFactory sessionFactory;

	private final BaseDaoImpl<CarBrand> carBrandDao;

	public CarBrandDaoImpl(SessionFactory sessionFactory) {
		carBrandDao = new BaseDaoImpl<>(sessionFactory);
	}

	@Override
	public void create(CarBrand obj) {
		carBrandDao.create(obj);
	}

	@Override
	public CarBrand findById(Class classType, long id) {
		return carBrandDao.findById(classType, id);
	}

	@Override
	public void update(CarBrand obj) {
		carBrandDao.update(obj);
	}

	@Override
	public void delete(CarBrand obj) {
		carBrandDao.delete(obj);
	}

	@Override
	public List<CarBrand> findAllBrandByName(String name) {
		String queryFormat = "SELECT * FROM t_cars_brand WHERE t_cars_brand.CB_NAME LIKE '%" + name + "%';";
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery(queryFormat, CarBrand.class).list();
		}
	}
}
