package dao;

import dao.base.BaseDaoTest;
import infrastructure.dao.PaymentDao;
import infrastructure.model.Payment;
import lombok.SneakyThrows;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentDaoImplTest extends BaseDaoTest {

	@Autowired
	PaymentDao targetObject;

	@Before
	public void setUp() throws Exception {
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
		ResultSet resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM PAYMENT;");
		resultSet.next();
		int initialSize = resultSet.getInt(1);
		assertEquals(7, initialSize);

		Payment payment = new Payment();
		LocalDateTime localDateTime = LocalDateTime.now();
//		payment.setDateTimePayment(Timestamp.valueOf(localDateTime));

		// when
		targetObject.create(payment);

		//then
		resultSet = connection.createStatement().executeQuery("SELECT count(*) FROM PAYMENT;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(8, actualSize);
		connection.createStatement().executeUpdate(String.format("delete from PAYMENT where ID=%d;"/*, payment.getId()*/));
		connection.close();
	}

	@Test
	public void findById() {
		//given

		//when
		Payment payment = targetObject.findById(1);

		//then
//		assertEquals("2022-01-01 13:10:00.0", payment.getDateTimePayment().toString());
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
//		payment.setId(101);
//		payment.setDateTimePayment(Timestamp.valueOf("2022-01-01 10:10:00"));

		//when
		targetObject.delete(payment);

		//then
		ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
				.executeQuery("SELECT count(*) FROM PAYMENT;");
		resultSet.next();
		int actualSize = resultSet.getInt(1);
		assertEquals(7, actualSize);
		DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
		resultSet.close();
	}
}