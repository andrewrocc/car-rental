package infrastructure.service;

import infrastructure.config.RepositoryConfig;
import infrastructure.dto.CarInfoDTO;
import infrastructure.model.Car;
import infrastructure.model.CarBrand;
import infrastructure.model.CarModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
@TestPropertySource(value = "classpath:/car_rental_test.jdbc.properties")
public class CarServiceTest {

    @Autowired
    private CarService targetObject;

    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private CarModelService carModelService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    public void getCarTable() {
        //given

        //when
        List<CarInfoDTO> carTable = targetObject.getCarTable();

        //then
        assertNotNull(carTable);
        assertEquals(8, carTable.size());
    }

    @Test
    public void add() {
        //given
        CarBrand carBrand = carBrandService.findById(1L);
        CarModel carModel = carModelService.findById(1L);
        Car car = Car.builder().number("1").price(new BigDecimal(1))
                .carBrand(carBrand).carModel(carModel).build();

        //when
        Car newCar = targetObject.add(car);

        //then
        assertNotNull(newCar);
        assertEquals(newCar.getNumber(), "1");
        Car carByNumber = targetObject.getCarByNumber("1");
        targetObject.delete(carByNumber.getId());
    }
}