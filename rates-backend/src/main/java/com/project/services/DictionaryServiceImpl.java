package com.project.services;

import com.project.dao.DictionaryDAO;
import com.project.entities.Currency;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author armdev
 */
@Service("dictionaryService")
@Qualifier("dictionaryService")
@Component
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryDAO dao;

    @Override
    public List<Currency> findCurrencyList() {
        return dao.findCurrencyList();
    }

   

}
