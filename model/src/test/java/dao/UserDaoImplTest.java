package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.UserDao;
import infrastructure.model.User;
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
public class UserDaoImplTest extends BaseDaoTest {

	@Autowired
	UserDao targetObject;

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
		ResultSet resultSet = connection.createStatement().executeQuery("select count(*) from USER;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		User namelessUser = new User();
		namelessUser.setFirstName("username");
		namelessUser.setLastName("test");
		namelessUser.setEmail("username@mail.com");
		namelessUser.setPaymentCard("1234567890987654");
		namelessUser.setPassword("123");

		// when
		targetObject.create(namelessUser);

		//then
		resultSet = connection.createStatement().executeQuery("select count(*) from USER;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from USER where ID=%d;", namelessUser.getId()));
		connection.close();
	}

	@Test
	@SneakyThrows
	public void findById() {
		//given

		//when
		User user = targetObject.findById(1L);

		//then
		assertEquals("Eugene", user.getFirstName());
		assertEquals("Onegin", user.getLastName());
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
		delete_user.setFirstName("Steve");
		delete_user.setLastName("Jobs");
		delete_user.setEmail("stevejob@icloud.com");
		delete_user.setPaymentCard("0987654321678906");
		delete_user.setPassword("111");
		delete_user.setId(101);

		//when
		targetObject.delete(delete_user);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("select count(*) from USER;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}