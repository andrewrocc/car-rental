package dao;

import models.Car;
import org.junit.Test;
import models.CarType;
import org.junit.After;
import models.CarBrand;
import models.CarModel;
import org.junit.Before;
import dao.base.DaoTest;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class CarDaoImplTest extends DaoTest {

	CarDaoImpl targetObject;

	@Before
	public void setUp() throws Exception {
		targetObject = new CarDaoImpl(testSessionFactory);
	}

	@After
	public void tearDown() throws Exception {
		targetObject = null;
	}

	@Test
	@SneakyThrows
	public void create() {
		//given
		Connection connection = testMysqlJdbcDataSource.getConnection();

		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_cars;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		CarType carType = new CarType();
		carType.setId(4);
		carType.setTypeName("sport car");

		CarModel carModel = new CarModel();
		carModel.setId(2);
		carModel.setModelName("AMG G63");

		CarBrand carBrand = new CarBrand();
		carBrand.setId(2);
		carBrand.setBrandName("Mercedes-Benz");

		Car car = new Car();
		car.setNumberCar("234e");
		car.setCarBrandId(carBrand.getId());
		car.setCarModelId(carModel.getId());
		car.setCarTypeId(carType.getId());

		car.setCarType(carType);
		car.setCarBrand(carBrand);
		car.setCarModel(carModel);

		carBrand.setCar(car);
		carModel.setCar(car);
		carType.setCar(car);
		// when
		targetObject.create(car);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_cars;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_cars where C_ID=%d;", car.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		Car car = targetObject.findById(1L);

		//then
		assertEquals("9874", car.getNumberCar());
	}

	@Test
	public void update() {
		create();
	}

	@Test
	@SneakyThrows
	public void delete() {
		//given
		IDataSet dataset = new FlatXmlDataSetBuilder()
				.build(UserDaoImplTest.class.getResourceAsStream("CarDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		Car cb = new Car();
		cb.setId(101);
		cb.setNumberCar("234e");

		//when
		targetObject.delete(cb);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_cars;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}