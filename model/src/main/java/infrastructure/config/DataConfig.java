package infrastructure.config;

import infrastructure.models.*;
import lombok.SneakyThrows;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "infrastructure")
@PropertySource(value = {
		"classpath:/car_rental.jdbc.properties",
		"classpath:/hibernate.properties"})
@EnableTransactionManagement
public class DataConfig {

	public static final String JDBC_PROPERTIES_FILE_NAME = "car_rental.jdbc.properties";

	public static final String HIBERNATE_PROPERTIES_FILE_NAME = "hibernate.properties";

	private static Properties jdbcProperties;

	@SneakyThrows
	public static Properties getJdbcProperties(String propertiesFileName) {
		if (jdbcProperties == null) {
			jdbcProperties = new Properties();
			jdbcProperties.load(MysqlJdbcDataSource.class.getClassLoader()
					.getResourceAsStream(propertiesFileName));
		}
		return jdbcProperties;
	}

	@Bean
	public Properties hibernateProperties(@Value("${hibernate.show_sql}") String showSql,
	                                      @Value("true") String debug,
	                                      @Value("${hibernate.dialect}") String dialect,
	                                      @Value("${hibernate.format_sql}") String format) {
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.show_sql", showSql);
		hibernateProperties.put("debug", debug);
		hibernateProperties.put("hibernate.dialect", dialect);
		hibernateProperties.put("hibernate.format_sql", format);

		return hibernateProperties;
	}

	@Bean
	public DataSource dataSource(@Value("${url}") String url,
	                             @Value("${driver}") String driverClassName,
	                             @Value("${username}") String userName,
	                             @Value("${password}") String password,
	                             @Value("true") boolean removeAbandonedOnBorrow,
	                             @Value("20") int initialSize,
	                             @Value("30") int maxTotal) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setRemoveAbandonedOnBorrow(removeAbandonedOnBorrow);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxTotal(maxTotal);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource,
	                                              Properties hibernateProperties) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setAnnotatedClasses(User.class,
				Payment.class,
				CarType.class,
				CarModel.class,
				CarBrand.class,
				Car.class,
				Order.class,
				M2M_UserOrder.class,
				M2M_OrderPayment.class,
				M2M_UserRole.class,
				Permission.class,
				Role.class);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
}
