package infrastructure.repository;

import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarModelRepositoryTest extends BaseDaoTest{

    @Autowired
    private CarModelRepository targetObject;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    @SneakyThrows
    public void findAllCarModelByName() {
        //given

        // when
        List<CarModel> carModels = targetObject.findAllCarModelByName("RS 5");

        //then
        assertEquals(1, carModels.size());
    }

    @Test
    @SneakyThrows
    public void findByBrandId() {
        // when
        CarModel carModels = targetObject.findByBrandId(1L);

        //then
        assertEquals("M3 competition", carModels.getModelName());
    }

    @Test
    @SneakyThrows
    public void create() {
        //given
        CarBrand carBrand = CarBrand.builder().id(101L).brandName("test").build();
        CarModel carModel = CarModel.builder().id(101L).modelName("test").carBrand(carBrand).build();

        //when
        targetObject.save(carModel);

        //then
        assertNotNull(targetObject.findByName("test"));
        Connection connection = testMysqlJdbcDataSource.getConnection();
        String queryModel = String.format("delete from CAR_MODEL where NAME='%s';", "test");
        String queryBrand = String.format("delete from CAR_BRAND where NAME='%s';", "test");
        connection.createStatement().executeUpdate(queryModel);
        connection.createStatement().executeUpdate(queryBrand);
        connection.close();
    }

    @Test
    @SneakyThrows
    public void delete() {
        //given
        IDataSet dataset = new FlatXmlDataSetBuilder()
                .build(CarModelRepositoryTest.class.getResourceAsStream("CarModelRepoTest.xml"));
        DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

        CarBrand carBrand = CarBrand.builder().id(101L).brandName("test").build();
        CarModel carModel = CarModel.builder().id(101L).modelName("test").carBrand(carBrand).build();

        //when
        targetObject.delete(carModel);

        //then
        ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
                .executeQuery("SELECT count(*) FROM CAR_MODEL;");
        resultSet.next();
        int actualSize = resultSet.getInt(1);
        assertEquals(8, actualSize);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
        resultSet.close();
    }
}