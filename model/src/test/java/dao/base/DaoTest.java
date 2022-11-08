package dao.base;

import models.*;
import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.hibernate.boot.Metadata;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.database.IDatabaseConnection;
import db.connection.properties.MysqlJdbcDataSource;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DaoTest {

	// JDBC data source
	public static MysqlJdbcDataSource testMysqlJdbcDataSource;

	// DBUnit connection
	public static IDatabaseConnection iDatabaseConnection;

	//Hibernate session factory
	public static SessionFactory testSessionFactory;


	// TODO: do not forget add addAnnotatedClass from models
	@BeforeClass
	@SneakyThrows
	public static void init() {
		testMysqlJdbcDataSource = new MysqlJdbcDataSource("car_rental_test.jdbc.properties");
		iDatabaseConnection = new MySqlConnection(testMysqlJdbcDataSource.getConnection(), "car_rental_test");

		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.configure("hibernate_test.cfg.xml").build();
		Metadata metadata = new MetadataSources(standardRegistry)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Payment.class)
				.addAnnotatedClass(CarType.class)
				.addAnnotatedClass(CarModel.class)
				.addAnnotatedClass(CarBrand.class)
				.addAnnotatedClass(Car.class)
				.addAnnotatedClass(Order.class)
				.getMetadataBuilder()
				.build();
		testSessionFactory = metadata.getSessionFactoryBuilder().build();
	}

	@AfterClass
	@SneakyThrows
	public static void destroy() {
		iDatabaseConnection.close();
		if (testSessionFactory != null && testSessionFactory.isOpen()) {
			testSessionFactory.close();
		}
	}
}
