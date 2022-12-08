package infrastructure.config;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MysqlJdbcDataSource {

	private final String propertyFileName;

	public static final String JDBC_PROPERTIES_FILE_NAME = "car_rental.jdbc.properties";

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

	@SneakyThrows
	public MysqlJdbcDataSource() {
		this(JDBC_PROPERTIES_FILE_NAME);
	}

	@SneakyThrows
	public MysqlJdbcDataSource(String propertyFileName) {
		this.propertyFileName = propertyFileName;
		Class.forName(getJdbcProperties(propertyFileName).getProperty("driver"));
	}

	@SneakyThrows
	public Connection getConnection() {
		Properties jdbcProperties = getJdbcProperties(propertyFileName);
		return DriverManager.getConnection(
				jdbcProperties.getProperty("url"),
				jdbcProperties.getProperty("username"),
				jdbcProperties.getProperty("password")
		);
	}
}
