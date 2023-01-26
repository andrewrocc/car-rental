package infrastructure.repository;

import infrastructure.model.CarBrand;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarBrandRepositoryTest extends BaseDaoTest {

    @Autowired
    private CarBrandRepository targetObject;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void findAllBrandByName() {
        //given
        Connection connection = testMysqlJdbcDataSource.getConnection();
        String query = "SELECT COUNT(*) FROM CAR_BRAND WHERE CAR_BRAND.NAME LIKE '%Audi%';";
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        resultSet.next();
        int initialSize = resultSet.getInt(1);
        assertEquals(1, initialSize);

        // when
        List<CarBrand> brandByName = targetObject.findAllBrandByName("Audi");

        //then
        assertEquals(1, brandByName.size());
        connection.close();
    }

    @Test
    @SneakyThrows
    public void findById() {
        //given
        Connection connection = testMysqlJdbcDataSource.getConnection();
        String query = "SELECT * FROM CAR_BRAND WHERE CAR_BRAND.ID=1;";
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        resultSet.next();
        int initialSize = resultSet.getInt(1);
        String queryBrandName = resultSet.getString(2);
        assertEquals(1, initialSize);

        // when
        Optional<CarBrand> carBrand = targetObject.findById(1L);

        //then
        carBrand.ifPresent(brand -> assertEquals(queryBrandName, brand.getBrandName()));
        connection.close();
    }

    @Test
    @SneakyThrows
    public void create() {
        //given

        //when
        targetObject.save(CarBrand.builder().id(101L).brandName("test").build());

        //then
        assertNotNull(targetObject.findByName("test"));
        Connection connection = testMysqlJdbcDataSource.getConnection();
        String query = String.format("delete from CAR_BRAND where NAME='%s';", "test");
        connection.createStatement().executeUpdate(query);
        connection.close();
    }

    @Test
    @SneakyThrows
    public void delete() {
        //given
        IDataSet dataset = new FlatXmlDataSetBuilder()
                .build(CarBrandRepositoryTest.class.getResourceAsStream("CarBrandRepoTest.xml"));
        DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

        CarBrand carBrand = CarBrand.builder().id(101L).brandName("test").build();

        //when
        targetObject.delete(carBrand);

        //then
        ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
                .executeQuery("SELECT count(*) FROM CAR_BRAND;");
        resultSet.next();
        int actualSize = resultSet.getInt(1);
        assertEquals(13, actualSize);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
        resultSet.close();
    }
}