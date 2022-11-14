package dao;

import dao.base.BaseDaoTest;
import lombok.SneakyThrows;
import models.CarBrand;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class CarBrandDaoImplTest extends BaseDaoTest {

	CarBrandDaoImpl targetObject;

	@Before
	public void setUp() throws Exception {
		targetObject = new CarBrandDaoImpl(testSessionFactory);
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
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_cars_brand;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		CarBrand cb = new CarBrand();
		cb.setBrandName("Bugatti");

		// when
		targetObject.create(cb);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_cars_brand;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_cars_brand where CB_ID=%d;", cb.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		CarBrand cb = targetObject.findById(1);

		//then
		assertEquals("BMW", cb.getBrandName());
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
				.build(UserDaoImplTest.class.getResourceAsStream("CarBrandDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		CarBrand cb = new CarBrand();
		cb.setId(101);
		cb.setBrandName("Bugatti");

		//when
		targetObject.delete(cb);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_cars_brand;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}