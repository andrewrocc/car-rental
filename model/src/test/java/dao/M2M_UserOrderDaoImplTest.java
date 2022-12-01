package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.M2M_UserOrderDao;
import infrastructure.models.M2M_UserOrder;
import infrastructure.models.Order;
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
public class M2M_UserOrderDaoImplTest extends BaseDaoTest {

	@Autowired
	M2M_UserOrderDao targetObject;

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
		IDataSet dataset = new FlatXmlDataSetBuilder()
				.build(UserDaoImplTest.class.getResourceAsStream("M2M_UserOrderDaoImplTestCreate.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_m2m_users_orders;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(8, initialSize);

		User user = new User();
		user.setId(101L);
//		user.setName("Valera");
//		user.setEmail("valervogne@mail.com");
//		user.setPaymentCard("3675901856749673");

		Order order = new Order();
		order.setId(101L);
//		order.setPrice(BigDecimal.valueOf(50.0));
//		order.setDateTime(Timestamp.valueOf("2022-10-10 10:10:10"));
//		order.setCarId(3);

		M2M_UserOrder userOrder = new M2M_UserOrder();
		userOrder.setUserId(user.getId());
		userOrder.setOrderId(order.getId());

		userOrder.setOrder(order);
		userOrder.setUser(user);

		// when
		targetObject.create(userOrder);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_m2m_users_orders;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(9, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_m2m_users_orders where U_ID=%d;", userOrder.getUserId()));
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		M2M_UserOrder userOrder = targetObject.findById(M2M_UserOrder.class, 1);

		//then
		assertEquals(1L, userOrder.getUserId());
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
				.build(UserDaoImplTest.class.getResourceAsStream("M2M_UserOrderDaoImplTestDelete.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		M2M_UserOrder userOrder = new M2M_UserOrder();
		userOrder.setUserId(101);
		userOrder.setOrderId(101);

		//when
		targetObject.delete(userOrder);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_m2m_users_orders;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}