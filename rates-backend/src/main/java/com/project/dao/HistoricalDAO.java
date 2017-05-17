package com.project.dao;

import com.project.entities.Historical;
import java.util.List;

/**
 *
 * @author armdev
 */
public interface HistoricalDAO {
  
    public Long save(Historical entity);

    public List<Historical> findAll();
    
    public Historical findByHistoricalDate(String historicalDate);


}
