package com.project.dao;

import com.project.entities.Currency;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author armdev
 */
@Component
@Repository("dictionaryDAO")
@Qualifier("dictionaryDAO")
@Transactional
public class DictionaryDAOImpl extends AbstractDao implements DictionaryDAO {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DictionaryDAOImpl.class);

    public DictionaryDAOImpl() {
        LOG.info("DictionaryDAO called");
    }

    @Override
    public List<Currency> findCurrencyList() {        
        List<Currency> list = getSession().createCriteria(Currency.class).list();      
        return list;
    }

}
