package dao;

import models.Payment;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import dao.base.DaoTest;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Connection;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class PaymentDaoImplTest extends DaoTest {

	PaymentDaoImpl targetObject;

	@Before
	public void setUp() throws Exception {
		targetObject = new PaymentDaoImpl(testSessionFactory);
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
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_payments;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		Payment payment = new Payment();
		LocalDateTime localDateTime = LocalDateTime.now();
		payment.setDtPayment(Timestamp.valueOf(localDateTime));

		// when
		targetObject.create(payment);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM t_payments;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from t_payments where P_ID=%d;", payment.getId()));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		Payment payment = targetObject.findById(1);

		//then
		assertEquals("2022-01-01 13:10:00.0", payment.getDtPayment().toString());
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
				.build(UserDaoImplTest.class.getResourceAsStream("PaymentDaoImplTest.xml"));
		DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

		Payment payment = new Payment();
		payment.setId(101);
		payment.setDtPayment(Timestamp.valueOf("2022-01-01 10:10:00"));

		//when
		targetObject.delete(payment);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("select count(*) from t_payments;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}