package infrastructure.service;

import infrastructure.config.RepositoryConfig;
import infrastructure.dto.UserDTO;
import infrastructure.model.Order;
import infrastructure.model.User;
import infrastructure.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepositoryConfig.class)
@TestPropertySource(value = "classpath:/car_rental_test.jdbc.properties")
public class UserServiceTest {

    @Autowired
    private UserService targetObject;

    private final String EMPTY_STRING = "";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        targetObject = null;
    }

    @Test
    public void getAllUsers() {
        //given
        PageRequest pageRequest = PageRequest.of(1, 3, Sort.by("id").ascending());

        //when
        List<User> usersPage = targetObject.getAllUsers(pageRequest);

        //then
        assertNotNull(usersPage);
        assertEquals(3, usersPage.size());
    }

    @Test
    public void update() {
        //given
        User userRef = targetObject.getUserById(8L);
        UserDTO userDTO = UserDTO.builder().id(userRef.getId()).firstName("admin").lastName(EMPTY_STRING)
                .email("admin@rentcar.com").paymentCard("1111111111111111")
                .password("123").isAdmin(true).build();

        //when
        targetObject.update(8L, userDTO);

        //then
        User user = targetObject.getUserById(8L);
        assertNotNull(user);
        assertEquals(user.getLastName(), EMPTY_STRING);
    }

    @Test(expected = ResponseStatusException.class)
    public void getUserById() {
        //given

        //when
        User userById = targetObject.getUserById(10000L);

        //then
    }
}