package dao;

import models.Car;
import org.hibernate.SessionFactory;
import db.connection.properties.MysqlSessionFactory;

public class CarDaoImpl implements CarDao {

	private final BaseDaoImpl<Car> carDao;

	public CarDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public CarDaoImpl(SessionFactory sessionFactory) {
		carDao = new BaseDaoImpl<>(sessionFactory, Car.class);
	}

	@Override
	public void create(Car obj) {
		carDao.create(obj);
	}

	@Override
	public Car findById(long id) {
		return carDao.findById(id);
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
