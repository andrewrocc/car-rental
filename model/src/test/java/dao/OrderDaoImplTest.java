package dao;

import dao.base.BaseDaoTest;
import lombok.SneakyThrows;
import models.Car;
import models.M2M_OrderPayment;
import models.M2M_UserOrder;
import models.Order;
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
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDaoImplTest extends BaseDaoTest {

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

		M2M_UserOrder userOrder = new M2M_UserOrder();
		userOrder.setUserId(1L);
		userOrder.setOrderId(1L);

		M2M_OrderPayment orderPayment = new M2M_OrderPayment();
		orderPayment.setOrderId(1L);
		orderPayment.setPaymentId(1L);

		Order order = new Order();
		order.setPrice(BigDecimal.valueOf(43.4));
		order.setDateTime(Timestamp.valueOf("2022-04-23 15:01:00"));
		order.setCarId(car.getId());

		order.setCar(car);
		order.setUserOrder(new HashSet<>(List.of(userOrder)));
		order.setOrderPayment(new HashSet<>(List.of(orderPayment)));

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
		order.setPrice(BigDecimal.valueOf(50.0));
		order.setDateTime(Timestamp.valueOf("2022-12-19 00:30:00"));
		order.setCarId(4L);

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