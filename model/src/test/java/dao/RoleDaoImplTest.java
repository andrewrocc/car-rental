package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.RoleDao;
import infrastructure.model.Role;
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
public class RoleDaoImplTest extends BaseDaoTest {

	@Autowired
	RoleDao targetObject;

	@Before
	public void setUp() throws Exception {	}

	@After
	public void tearDown() throws Exception {
		targetObject = null;
	}

	@Test
	@SneakyThrows
	public void create() {
		//given
		Connection connection = testMysqlJdbcDataSource.getConnection();
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM ROLE;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(3, initialSize);

		Role role = new Role();
		role.setName("test");
		role.setDescription("test");

		// when
		targetObject.create(role);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM ROLE;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(4, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from ROLE where ID=%d;", role.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		Role role = targetObject.findById(1L);

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
				.executeQuery("SELECT count(*) FROM ROLE;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(3, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}