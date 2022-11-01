package db.connection.properties;

import lombok.SneakyThrows;

import java.util.Properties;

public class DataConfig {

	public static final String JDBC_PROPERTIES_FILE_NAME = "car_rental.jdbc.properties";

	public static final String HIBERNATE_PROPERTIES_FILE_NAME = "hibernate.properties";

	private static Properties jdbcProperties;
	private static Properties hibernateProperties;

	@SneakyThrows
	public static Properties getJdbcProperties(String propertiesFileName) {
		if (jdbcProperties == null) {
			jdbcProperties = new Properties();
			jdbcProperties.load(MysqlJdbcDataSource.class.getClassLoader()
					.getResourceAsStream(propertiesFileName));
		}
		return jdbcProperties;
	}

	@SneakyThrows
	public static Properties getHibernateProperties(String propertiesFileName) {
		if (hibernateProperties == null) {
			hibernateProperties = new Properties();
			hibernateProperties.load(MysqlJdbcDataSource.class.getClassLoader()
					.getResourceAsStream(propertiesFileName));
		}
		return hibernateProperties;
	}
}
