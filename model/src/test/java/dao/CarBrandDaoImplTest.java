package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.CarBrandDao;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarBrandDaoImplTest extends BaseDaoTest {

	@Autowired
	CarBrandDao targetObject;

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
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM CAR_BRAND;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		CarBrand cb = new CarBrand();
		cb.setBrandName("Bugatti");

		// when
		targetObject.create(cb);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM CAR_BRAND;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from CAR_BRAND where ID=%d;", cb.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		CarBrand cb = targetObject.findById(1L);

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
				.executeQuery("SELECT count(*) FROM CAR_BRAND;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}

	@Test
	@SneakyThrows
	public void getAllCarBrands() {
		Connection connection = testMysqlJdbcDataSource.getConnection();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM CAR_BRAND;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		// when
		List<CarBrand> brandList = targetObject.getAllCarBrands();

		//then
		assertEquals(7, brandList.size());
		connection.close();
	}
}