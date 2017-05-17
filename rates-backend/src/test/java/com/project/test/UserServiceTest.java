package com.project.test;

import com.project.entities.User;
import com.project.services.UserService;
import com.project.test.config.TestCoreConfig;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UserServiceTest.class);

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Autowired
    @Qualifier("userService")
    private final UserService instance = null;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void test1UserSave() {
        LOG.info("1. Save User entity");
        Date currentDate = new Date();
        User entity = new User("Jeck", "Smith", "mail@gmail.de", "123456", currentDate, currentDate, currentDate, "Germany", "Berlin", "First Street", "0554856");
        Long userId = instance.save(entity);
        LOG.info("Saved new user: returned id " + userId);
        assertNotNull(userId);
    }

    @Test
    public void test2FindUserById() {
        LOG.info("2. Test Find User By Id ");
        User returnedUser = instance.findUser(1l);
        assertNotNull(returnedUser);
    }

    @Test
    public void test3FindUserByFakeId() {
        LOG.info("3. Find user by fake id ");
        User doNotFindUser = instance.findUser(0L);
        assertNull(doNotFindUser);
    }

    @Test
    public void test4FindUserLogin() {
        LOG.info("4. User Success login ");
        User findUser = instance.findUser(1L);
        assertNotNull(findUser);

        User loggedUser = instance.userLogin(findUser.getEmail(), "123456");
        assertNotNull(loggedUser);
    }

    @Test
    public void test5FindUserUnsuccessLogin() {
        LOG.info("5. User unuccess login ");
        User findUser = instance.findUser(1L);
        assertNotNull(findUser);

        User unsuccess = instance.userLogin(findUser.getEmail(), "7777777");
        assertNull(unsuccess);
    }

    @Test
    public void test6FindCheckUserEmailForUpdateFalse() {
        LOG.info("6. Check User Email For Update False: ");
        boolean checkUserEmailForUpdate = instance.checkUserEmailForUpdate(1L, "maild@gmail.de");
        assertEquals(checkUserEmailForUpdate, Boolean.FALSE);
    }

    @Test
    public void test7FindCheckUserEmailForUpdateTrue() {
        LOG.info("7. Check User Email For Update True : ");        
        boolean checkUserEmailForUpdateTrue = instance.checkUserEmailForUpdate(0L, "mail@gmail.de");
        assertEquals(checkUserEmailForUpdateTrue, Boolean.TRUE);
    }

    @Test
    public void test8FindUserByEmail() {
        LOG.info("8. Find user by Email ");
        User findByEmail = instance.getByEmail("mail@gmail.de");
        assertNotNull(findByEmail);
    }

    @Test
    public void test9FindUserByFakeEmail() {
        LOG.info("9. Find user by fake Email ");
        User findByFakeEmail = instance.getByEmail("mail-fake@gmail.de");
        assertNull(findByFakeEmail);
    }

    @Test
    public void test91FindChangePassword() {
        LOG.info("10. Change password");
        LOG.info("Store user get id");
        Date currentDate = new Date();
        User entity = new User("Jeck", "Smith", "gmailj@gmail.de", "123456", currentDate, currentDate, currentDate, "Germany", "Berlin", "First Street", "0554856");
        Long userId = instance.save(entity);
        LOG.info("updating password " + userId);
        int value = instance.updatePassword(userId, "qqqqqq");
        assertEquals(1L, value);
    }

    @Test
    public void test92FindChangePasswordFalse() {
        LOG.info("11. Change password unsuccess");
        int value = instance.updatePassword(8888L, "qqqqqq");
        assertEquals(value, 0);
    }

    @Test
    public void test93UpdateUserEntity() {
        LOG.info("12. Update user");
        Date currentDate = new Date();
        User entity = new User("Anna", "Smith", "dodojl@gmail.de", "123456", currentDate, currentDate, currentDate, "Germany", "Berlin", "First Street", "0554856");
        Long userId = instance.save(entity);
        entity.setId(userId);
        Long value = instance.update(entity);
        assertEquals(userId, value);
    }

}
