package dao;

import models.User;
import org.junit.Test;
import org.junit.After;
import dao.base.DaoTest;
import org.junit.Before;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class UserDaoImplTest extends DaoTest {

	UserDaoImpl targetObject;

	@Before
	public void setUp() throws Exception {
		targetObject = new UserDaoImpl(testSessionFactory);
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
		ResultSet resultSet = connection.createStatement().executeQuery("select count(*) from t_users;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		User namelessUser = new User();
		namelessUser.setName("username");
		namelessUser.setEmail("username@mail.com");
		namelessUser.setPaymentCard("1234567890987654");

		// when
		targetObject.create(namelessUser);

		//then
		resultSet = connection.createStatement().executeQuery("select count(*) from t_users;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_users where U_ID=%d;", namelessUser.getId()));
		connection.close();
	}

	@Test
	@SneakyThrows
	public void findById() {
		//given

		//when
		User user = targetObject.findById(1);

		//then
		assertEquals("Eugene Onegin", user.getName());
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
				.build(UserDaoImplTest.class.getResourceAsStream("UserDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		User delete_user = new User();
		delete_user.setName("Steve Jobs");
		delete_user.setEmail("stevejob@icloud.com");
		delete_user.setPaymentCard("0987654321678906");
		delete_user.setId(101);

		//when
		targetObject.delete(delete_user);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("select count(*) from t_users;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}