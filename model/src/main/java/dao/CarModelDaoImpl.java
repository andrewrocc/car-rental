package dao;

import db.connection.properties.MysqlSessionFactory;
import models.CarModel;
import org.hibernate.SessionFactory;

public class CarModelDaoImpl implements CarModelDao {

	private final BaseDaoImpl<CarModel> carModel;

	public CarModelDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public CarModelDaoImpl(SessionFactory sessionFactory) {
		carModel = new BaseDaoImpl<>(sessionFactory, CarModel.class);
	}

	@Override
	public void create(CarModel obj) {
		carModel.create(obj);
	}

	@Override
	public CarModel findById(long id) {
		return carModel.findById(id);
	}

	@Override
	public void update(CarModel obj) {
		carModel.update(obj);
	}

	@Override
	public void delete(CarModel obj) {
		carModel.delete(obj);
	}
}
