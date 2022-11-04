package dao;

import dao.base.DaoTest;
import lombok.SneakyThrows;
import models.CarModel;
import models.CarType;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class CarModelDaoImplTest extends DaoTest {

	CarModelDaoImpl targetObject;

	@Before
	public void setUp() throws Exception {
		targetObject = new CarModelDaoImpl(testSessionFactory);
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
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_cars_model;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		CarModel cm = new CarModel();
		cm.setModelName("M7");

		// when
		targetObject.create(cm);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_cars_model;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_cars_model where CM_ID=%d;", cm.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		CarModel cm = targetObject.findById(1);

		//then
		assertEquals("M3 competition", cm.getModelName());
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
				.build(UserDaoImplTest.class.getResourceAsStream("CarModelDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		CarModel cm = new CarModel();
		cm.setId(101);
		cm.setModelName("M7");

		//when
		targetObject.delete(cm);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_cars_model;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}