package com.project.dao;

import com.project.entities.Currency;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author armdev
 */
@Component
@Repository("dictionaryDAO")
@Qualifier("dictionaryDAO")
public class DictionaryDAOImpl extends AbstractDao implements DictionaryDAO {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DictionaryDAOImpl.class);

    public DictionaryDAOImpl() {
        LOG.info("DictionaryDAO called");
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Currency> findCurrencyList() {
        List<Currency> currecnyList = new ArrayList<>();
        try {
            Query query = getSession().createQuery("SELECT c FROM Currency c ORDER BY c.currency ASC");
            currecnyList = query.list();
        } catch (Exception e) {
            LOG.info("Error occured in currecny list");
        }
        return currecnyList;
    }

}
