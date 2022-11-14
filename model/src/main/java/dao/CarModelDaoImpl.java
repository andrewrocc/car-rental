package dao;

import dao.base.BaseDaoImpl;
import db.connection.properties.MysqlSessionFactory;
import models.CarModel;
import org.hibernate.SessionFactory;

public class CarModelDaoImpl implements CarModelDao {

	private final BaseDaoImpl<CarModel> carModelDao;

	public CarModelDaoImpl() {
		this(MysqlSessionFactory.getInstance());
	}

	public CarModelDaoImpl(SessionFactory sessionFactory) {
		carModelDao = new BaseDaoImpl<>(sessionFactory, CarModel.class);
	}

	@Override
	public void create(CarModel obj) {
		carModelDao.create(obj);
	}

	@Override
	public CarModel findById(long id) {
		return carModelDao.findById(id);
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
