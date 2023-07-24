package infrastructure.service;

import infrastructure.config.RepositoryConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
@TestPropertySource(value = "classpath:/car_rental_test.jdbc.properties")
public class CarInfoServiceTest { }