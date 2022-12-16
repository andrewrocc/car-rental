package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.CarTypeDao;
import lombok.SneakyThrows;
import infrastructure.model.CarType;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
public class CarTypeDaoImplTest extends BaseDaoTest {

	@Autowired
	CarTypeDao targetObject;

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
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_cars_type;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(6, initialSize);

		CarType ct = new CarType();
//		ct.setTypeName("pickup");

		// when
		targetObject.create(ct);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_cars_type;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
//		connection.createStatement().executeUpdate(String.format("delete from t_cars_type where CT_ID=%d;", ct.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		CarType ct = targetObject.findById(1L);

		//then
//		assertEquals("sedan", ct.getTypeName());
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
				.build(UserDaoImplTest.class.getResourceAsStream("CarTypeDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		CarType ct = new CarType();
//		ct.setId(101);
//		ct.setTypeName("pickup");

		//when
		targetObject.delete(ct);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_cars_type;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(6, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}