package dao;

import dao.base.BaseDaoImpl;
import models.CarType;
import org.hibernate.SessionFactory;
import db.connection.properties.MysqlSessionFactory;

public class CarTypeDaoImpl implements CarTypeDao {

	private final BaseDaoImpl<CarType> carTypeDao;

	public CarTypeDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public CarTypeDaoImpl(SessionFactory sessionFactory) {
		carTypeDao = new BaseDaoImpl<>(sessionFactory, CarType.class);
	}

	@Override
	public void create(CarType obj) {
		carTypeDao.create(obj);
	}

	@Override
	public CarType findById(long id) {
		return carTypeDao.findById(id);
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
