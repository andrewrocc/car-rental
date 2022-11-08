package dao;

import dao.base.DaoTest;
import lombok.SneakyThrows;
import models.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class OrderDaoImplTest extends DaoTest {

	OrderDaoImpl targetObject;

	@Before
	public void setUp() throws Exception {
		targetObject = new OrderDaoImpl(testSessionFactory);
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

		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_orders;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		Car car = new Car();
		car.setId(4);
		car.setNumberCar("1246");
		car.setCarBrandId(2);
		car.setCarModelId(2);
		car.setCarTypeId(1);

		User user = new User();
		user.setId(1);
		user.setName("Eugene Onegin");
		user.setEmail("EugeneOnegin@mail.com");
		user.setPaymentCard("1111222233334444");

		Payment payment = new Payment();
		payment.setId(6);
		payment.setDtPayment(Timestamp.valueOf("2022-04-23 14:34:00"));

		Order order = new Order();
		order.setPrice(BigDecimal.valueOf(43.4));
		order.setDateTime(Timestamp.valueOf("2022-04-23 15:01:00"));
		order.setUserId(user.getId());
		order.setCarId(car.getId());
		order.setPaymentId(payment.getId());

		order.setUser(user);
		order.setPayment(payment);
		order.setCar(car);

		// when
		targetObject.create(order);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_orders;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_orders where O_ID=%d;", order.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		Order order = targetObject.findById(1L);

		//then
		assertEquals(BigDecimal.valueOf(324.7), order.getPrice());
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
				.build(OrderDaoImplTest.class.getResourceAsStream("OrderDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		Order order = new Order();
		order.setId(101);
		order.setPrice(BigDecimal.valueOf(234.4));
		order.setDateTime(Timestamp.valueOf("2023-01-01 00:56:10"));

		//when
		targetObject.delete(order);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_orders;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}