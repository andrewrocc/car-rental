package dao;

import models.CarType;
import org.hibernate.SessionFactory;
import db.connection.properties.MysqlSessionFactory;

public class CarTypeDaoImpl implements CarTypeDao {

	private final BaseDaoImpl<CarType> carType;

	public CarTypeDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public CarTypeDaoImpl(SessionFactory sessionFactory) {
		carType = new BaseDaoImpl<>(sessionFactory, CarType.class);
	}

	@Override
	public void create(CarType obj) {
		carType.create(obj);
	}

	@Override
	public CarType findById(long id) {
		return carType.findById(id);
	}

	@Override
	public void update(CarType obj) {
		carType.update(obj);
	}

	@Override
	public void delete(CarType obj) {
		carType.delete(obj);
	}
}
