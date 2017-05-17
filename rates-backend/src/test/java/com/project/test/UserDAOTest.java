package com.project.test;

import com.project.dao.UserDAO;
import com.project.entities.User;
import com.project.test.config.TestCoreConfig;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author armdev
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCoreConfig.class}, loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class UserDAOTest {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UserDAOTest.class);

    @BeforeClass
    public static void setUpClass() {       

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Autowired
    @Qualifier("userDAO")   
    private final UserDAO instance = null;

    @Before
    public void setUp() {     

    }

    @After
    public void tearDown() {

    }

    /**
     * Test of UserDAO
     */
    @Test
    public void testUserDAO() {
        LOG.info("Start Testing UserDAO");

        Date currentDate = new Date();

        User entity = new User("Jeck", "Smith", "mail@gmail.com", "123456", currentDate, currentDate, currentDate, "Germany", "Berlin", "First Street", "0554856");        

        Long result = instance.save(entity);
        LOG.info("1. Saved new user: returned id " + result);

        assertNotNull(result);

        LOG.info("2. Find user by id " + result);
        User returnedUser = instance.findUser(result);

        assertNotNull(returnedUser);

        LOG.info("3. Find user by fake id ");
        User doNotFindUser = instance.findUser(0L);
        assertNull(doNotFindUser);

        LOG.info("4. Found User by Id " + returnedUser.getFirstname() + " " + returnedUser.getLastname());
        assertEquals(returnedUser.getEmail(), entity.getEmail());

        User loggedUser = instance.userLogin(returnedUser.getEmail(), "123456");

        LOG.info("5. User login Success " + loggedUser.toString());
        assertNotNull(loggedUser);

        User unsuccess = instance.userLogin(returnedUser.getEmail(), "0123456");

        LOG.info("6. User login unuccess ");

        assertNull(unsuccess);

        LOG.info("7. Check User Email For Update False: ");
        boolean checkUserEmailForUpdate = instance.checkUserEmailForUpdate(result, loggedUser.getEmail());

        assertEquals(checkUserEmailForUpdate, Boolean.FALSE);

        LOG.info("8. Check User Email For Update True : ");
        boolean checkUserEmailForUpdateTrue = instance.checkUserEmailForUpdate(0L, loggedUser.getEmail());

        assertEquals(checkUserEmailForUpdateTrue, Boolean.TRUE);

        LOG.info("9. Deleting user with not existing id : ");
        boolean checkFail = instance.delete(0L);

        LOG.info("10. CheckFail  " + checkFail);

        assertEquals(checkFail, Boolean.FALSE);

        boolean check = instance.delete(result);

        LOG.info("11. Delete User with id   " + result + " " + check);

        assertEquals(check, Boolean.TRUE);
    }

}
