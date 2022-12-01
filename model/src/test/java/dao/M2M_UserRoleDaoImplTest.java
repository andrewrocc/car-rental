package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.M2M_UserRoleDao;
import infrastructure.models.M2M_UserRole;
import infrastructure.models.Role;
import infrastructure.models.User;
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
public class M2M_UserRoleDaoImplTest extends BaseDaoTest {

	@Autowired
	M2M_UserRoleDao targetObject;

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
		IDataSet dataset = new FlatXmlDataSetBuilder()
				.build(UserDaoImplTest.class.getResourceAsStream("M2M_UserRoleDaoImplTestCreate.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_m2m_users_roles;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		User user = new User();
		user.setId(101L);
		Role role = new Role();
		role.setId(101L);

		M2M_UserRole userOrder = new M2M_UserRole();
		userOrder.setUserId(user.getId());
		userOrder.setRoleId(role.getId());

		userOrder.setRole(role);
		userOrder.setUser(user);

		// when
		targetObject.create(userOrder);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_m2m_users_roles;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_m2m_users_roles where U_ID=%d;", userOrder.getUserId()));
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		M2M_UserRole userRole = targetObject.findById(M2M_UserRole.class, 1);

		//then
		assertEquals(2L, userRole.getRoleId());
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
				.build(UserDaoImplTest.class.getResourceAsStream("M2M_UserRoleDaoImplTestDelete.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		M2M_UserRole userRole = new M2M_UserRole();
		userRole.setUserId(101);
		userRole.setRoleId(101);

		//when
		targetObject.delete(userRole);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_m2m_users_roles;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}