package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.CarModelDao;
import infrastructure.model.CarModel;
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
public class CarModelDaoImplTest extends BaseDaoTest {

	@Autowired
	CarModelDao targetObject;

	@Before
	public void setUp() throws Exception { }

	@After
	public void tearDown() throws Exception {
		targetObject = null;
	}

	@Test
	@SneakyThrows
	public void create() {
		//given
		Connection connection = testMysqlJdbcDataSource.getConnection();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM CAR_MODEL;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		CarModel cm = new CarModel();
		cm.setCarBrandId(1L);
		cm.setModelName("M7");

		// when
		targetObject.create(cm);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM CAR_MODEL;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from CAR_MODEL where ID=%d;", cm.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		CarModel cm = targetObject.findById(1L);

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
				.executeQuery("SELECT count(*) FROM CAR_MODEL;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}