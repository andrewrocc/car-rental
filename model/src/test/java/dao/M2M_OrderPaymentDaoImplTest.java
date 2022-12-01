package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.M2M_OrderPaymentDao;
import infrastructure.models.M2M_OrderPayment;
import infrastructure.models.Order;
import infrastructure.models.Payment;
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

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class M2M_OrderPaymentDaoImplTest extends BaseDaoTest {

	@Autowired
	M2M_OrderPaymentDao targetObject;

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
				.build(UserDaoImplTest.class.getResourceAsStream("M2M_OrderPaymentDaoImplTestCreate.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_m2m_orders_payments;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(8, initialSize);

		Payment payment = new Payment();
		payment.setDateTimePayment(Timestamp.valueOf("2022-10-10 10:10:10"));
		payment.setId(101);

		Order order = new Order();
		order.setId(101);
		order.setPrice(BigDecimal.valueOf(600.0));
		order.setDateTime(Timestamp.valueOf("2022-10-10 10:10:10"));
		order.setCarId(3);

		M2M_OrderPayment orderPayment = new M2M_OrderPayment();
		orderPayment.setOrderId(order.getId());
		orderPayment.setPaymentId(payment.getId());

		orderPayment.setPayment(payment);
		orderPayment.setOrder(order);

		// when
		targetObject.create(orderPayment);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_m2m_orders_payments;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(9, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_m2m_orders_payments where O_ID=%d;", orderPayment.getOrderId()));
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		M2M_OrderPayment orderPayment = targetObject.findById(M2M_OrderPayment.class, 1);

		//then
		assertEquals(1L, orderPayment.getOrderId());
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
				.build(UserDaoImplTest.class.getResourceAsStream("M2M_OrderPaymentDaoImplTestDelete.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		M2M_OrderPayment orderPayment = new M2M_OrderPayment();
		orderPayment.setOrderId(101);
		orderPayment.setPaymentId(101);

		//when
		targetObject.delete(orderPayment);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM t_m2m_orders_payments;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}