package infrastructure.service;

import infrastructure.config.RepositoryConfig;
import infrastructure.dto.CarInfoDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
@TestPropertySource(value = "classpath:/car_rental_test.jdbc.properties")
public class CarInfoServiceTest {

    @Autowired
    private CarInfoService targetObject;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    public void getCarInfoById() {
        //given

        //when
        CarInfoDTO carInfoDTO = targetObject.getCarInfoById(1L);

        //then
        assertNotNull(carInfoDTO);
        assertEquals(carInfoDTO.getNumber(), "9874");
    }
}