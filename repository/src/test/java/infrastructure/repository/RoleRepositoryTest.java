package infrastructure.repository;

import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import infrastructure.model.Role;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class RoleRepositoryTest extends BaseDaoTest {

    @Autowired
    private RoleRepository targetObject;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    public void findById() {
        //given

        //when
        Optional<Role> role = targetObject.findById(1L);

        //then
        role.ifPresent(value -> assertEquals("admin", value.getName()));
    }

    @Test
    @SneakyThrows
    public void create() {
        //given

        Role role = Role.builder().id(3L).name("guest").description("none").build();

        //when
        targetObject.save(role);

        //then
        assertEquals(role.getName(), targetObject.findByNameStartsWith("guest").get().getName());
        Connection connection = testMysqlJdbcDataSource.getConnection();
        String queryModel = String.format("delete from ROLE where NAME='%s';", role.getName());
        connection.createStatement().executeUpdate(queryModel);
        connection.close();
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
                .build(RoleRepositoryTest.class.getResourceAsStream("RoleRepoTest.xml"));
        DatabaseOperation.INSERT.execute(iDatabaseConnection, dataset);

        //when
        targetObject.deleteById(101L);

        //then
        ResultSet resultSet = testMysqlJdbcDataSource.getConnection().createStatement()
                .executeQuery("SELECT count(*) FROM ROLE;");
        resultSet.next();
        int actualSize = resultSet.getInt(1);
        assertEquals(2, actualSize);
        DatabaseOperation.DELETE.execute(iDatabaseConnection, dataset);
        resultSet.close();
    }
}