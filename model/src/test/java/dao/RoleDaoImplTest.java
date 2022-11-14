package dao;

import dao.base.BaseDaoTest;
import lombok.SneakyThrows;
import models.Permission;
import models.Role;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class RoleDaoImplTest extends BaseDaoTest {

	RoleDaoImpl targetObject;

	@Before
	public void setUp() throws Exception {
		targetObject = new RoleDaoImpl(testSessionFactory);
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
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_roles;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(3, initialSize);

		Role role = new Role();
		role.setName("test");
		role.setDescription("test");

		// when
		targetObject.create(role);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_roles;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(4, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_roles where R_ID=%d;", role.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		Role role = targetObject.findById(1);

		//then
		assertEquals("admin", role.getName());
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
				.build(UserDaoImplTest.class.getResourceAsStream("RoleDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		Role role = new Role();
		role.setId(101);

		//when
		targetObject.delete(role);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_roles;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(3, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}