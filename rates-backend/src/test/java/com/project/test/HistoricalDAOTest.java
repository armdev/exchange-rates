package com.project.test;

import com.project.dao.HistoricalDAO;
import com.project.entities.Historical;
import com.project.test.config.TestCoreConfig;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
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
public class HistoricalDAOTest {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(HistoricalDAOTest.class);

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Autowired
    @Qualifier("historicalDAO")
    private final HistoricalDAO instance = null;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void test1HistoricalSave() {
        LOG.info("1. Save Historical entity");      
        Historical entity = new Historical(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, "2005-02-02");
        Long returnedId = instance.save(entity);
        LOG.info("Saved new user: returned id " + returnedId);
        assertNotNull(returnedId);
    }

    @Test
    public void test2findByHistoricalDate() {
        LOG.info("2. Test findByHistoricalDate ");
        Historical entity = instance.findByHistoricalDate("2005-02-02");
        assertNotNull(entity);
    }

    @Test
    public void test3FindAll() {
        LOG.info("3. User unuccess login ");
        List<Historical> list = instance.findAll();
        assertNotNull(list);

    }

}
