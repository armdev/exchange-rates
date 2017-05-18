package com.project.test;

import com.project.dao.DictionaryDAO;
import com.project.entities.Currency;
import com.project.services.DictionaryService;
import com.project.test.config.TestCoreConfig;
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
public class DictionaryTest {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DictionaryTest.class);

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Autowired
    @Qualifier("dictionaryDAO")
    private final DictionaryDAO instance = null;

    @Autowired
    @Qualifier("dictionaryService")
    private final DictionaryService serviceInstance = null;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testDaoFindCurrencyList() {
        LOG.info("testDaoFindCurrencyList ");
        List<Currency> list = instance.findCurrencyList();
        assertNotNull(list);
    }
    
    
    @Test
    public void testServiceFindCurrencyList() {
        LOG.info("testServiceFindCurrencyList ");
        List<Currency> list = serviceInstance.findCurrencyList();
        assertNotNull(list);
    }

}
