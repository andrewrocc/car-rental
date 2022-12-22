package infrastructure.repository;

import infrastructure.config.DataConfig;
import lombok.SneakyThrows;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.database.IDatabaseConnection;
import infrastructure.config.MysqlJdbcDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(value = "classpath:/car_rental_test.jdbc.properties")
@ContextConfiguration(classes = DataConfig.class)
public class BaseDaoTest {

    // JDBC data source
    public static MysqlJdbcDataSource testMysqlJdbcDataSource;

    // DBUnit connection
    public static IDatabaseConnection iDatabaseConnection;

    @BeforeClass
    @SneakyThrows
    public static void init() {
        testMysqlJdbcDataSource = new MysqlJdbcDataSource("car_rental_test.jdbc.properties");
        iDatabaseConnection = new MySqlConnection(testMysqlJdbcDataSource.getConnection(), "car_rental_test");
    }

    @AfterClass
    @SneakyThrows
    public static void destroy() {
        iDatabaseConnection.close();
    }
}