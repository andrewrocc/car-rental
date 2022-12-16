package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.CarDao;
import infrastructure.model.Car;
import infrastructure.model.CarBrand;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarDaoImplTest extends BaseDaoTest {

	@Autowired
	CarDao targetObject;

	@Before
	public void setUp() { }

	@After
	public void tearDown() throws Exception {
		targetObject = null;
	}

	@Test
	@SneakyThrows
	public void create() {
		//given
		Connection connection = testMysqlJdbcDataSource.getConnection();

		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM CAR;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		CarBrand carBrand = new CarBrand();
		carBrand.setId(2);
		carBrand.setBrandName("Mercedes-Benz");

		Car car = new Car();
		car.setNumberCar("234e");
		car.setCarBrandId(carBrand.getId());

		car.setCarBrand(carBrand);;

		// when
		targetObject.create(car);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM CAR;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from CAR where ID=%d;", car.getId()));
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
				.executeQuery("SELECT count(*) FROM CAR;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}