package dao;

import models.CarBrand;
import org.hibernate.SessionFactory;
import db.connection.properties.MysqlSessionFactory;

public class CarBrandDaoImpl implements CarBrandDao {

	private final BaseDaoImpl<CarBrand> carBrandDao;

	public CarBrandDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public CarBrandDaoImpl(SessionFactory sessionFactory) {
		carBrandDao = new BaseDaoImpl<>(sessionFactory, CarBrand.class);
	}

	@Override
	public void create(CarBrand obj) {
		carBrandDao.create(obj);
	}

	@Override
	public CarBrand findById(long id) {
		return carBrandDao.findById(id);
	}

	@Override
	public void update(CarBrand obj) {
		carBrandDao.update(obj);
	}

	@Override
	public void delete(CarBrand obj) {
		carBrandDao.delete(obj);
	}
}
