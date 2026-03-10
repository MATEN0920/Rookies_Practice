package mylab.user.di.annot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {
        assertNotNull(userService);

        assertNotNull(userService.getUserRepository());

        assertEquals("MySQL", userService.getUserRepository().getDbType());

        assertNotNull(userService.getSecurityService());

        assertTrue(userService.registerUser("hong", "홍길동", "1234"));
    }
}