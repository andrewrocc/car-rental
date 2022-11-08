package dao;

import models.CarBrand;
import org.hibernate.SessionFactory;
import db.connection.properties.MysqlSessionFactory;

public class CarBrandDaoImpl implements CarBrandDao {

	private final BaseDaoImpl<CarBrand> carBrand;

	public CarBrandDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public CarBrandDaoImpl(SessionFactory sessionFactory) {
		carBrand = new BaseDaoImpl<>(sessionFactory, CarBrand.class);
	}

	@Override
	public void create(CarBrand obj) {
		carBrand.create(obj);
	}

	@Override
	public CarBrand findById(long id) {
		return carBrand.findById(id);
	}

	@Override
	public void update(CarBrand obj) {
		carBrand.update(obj);
	}

	@Override
	public void delete(CarBrand obj) {
		carBrand.delete(obj);
	}
}
