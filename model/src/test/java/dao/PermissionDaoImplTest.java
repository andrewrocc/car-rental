package dao;

import dao.base.BaseDaoTest;
import lombok.SneakyThrows;
import models.Payment;
import models.Permission;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PermissionDaoImplTest extends BaseDaoTest {

	PermissionDaoImpl targetObject;

	@Before
	public void setUp() throws Exception {
		targetObject = new PermissionDaoImpl(testSessionFactory);
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
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_permissions;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(16, initialSize);

		Permission permission = new Permission();
		permission.setName("test");
		permission.setRoleId(1);

		// when
		targetObject.create(permission);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_permissions;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(17, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_permissions where PRMS_ID=%d;", permission.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		Permission permission = targetObject.findById(1);

		//then
		assertEquals("create_car", permission.getName());
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
				.build(UserDaoImplTest.class.getResourceAsStream("PermissionDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		Permission permission = new Permission();
		permission.setId(101);

		//when
		targetObject.delete(permission);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_permissions;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(16, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}