package com.project.services;

import com.project.dao.HistoricalDAO;
import com.project.entities.Historical;
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
@Service("historicalService")
@Qualifier("historicalService")
@Component
public class HistoricalServiceImpl implements HistoricalService {

    @Autowired
    private HistoricalDAO dao;

    @Override
    public Long save(Historical entity) {
        return dao.save(entity);
    }

    @Override
    public List<Historical> findAll() {
       return dao.findAll();
    }

    @Override
    public Historical findByHistoricalDate(String historicalDate) {
        return dao.findByHistoricalDate(historicalDate);
    }    

}
