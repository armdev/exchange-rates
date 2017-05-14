package com.project.dao;

import com.project.entities.Currency;
import java.util.List;
import org.hibernate.Query;
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
        // Query query = getSession().createQuery("select id, currency from Currency ORDER BY currency ASC");
        List<Currency> list = getSession().createCriteria(Currency.class).list();
  for(Currency c :list){
            LOG.info("LIST getCurrency " + c.getCurrency());
            System.out.println("LIST wwwwwwwwwwwwwwwww " + c.getCurrency());
        }
        return list;
    }

}
