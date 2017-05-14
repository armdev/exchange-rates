package com.project.dao;

import com.project.entities.Currency;
import java.util.List;

/**
 *
 * @author armdev
 */
public interface DictionaryDAO {

    public List<Currency> findCurrencyList();

}
