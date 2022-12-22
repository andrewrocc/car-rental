package infrastructure.repository;

import infrastructure.model.CarBrand;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
    @Ignore
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
}