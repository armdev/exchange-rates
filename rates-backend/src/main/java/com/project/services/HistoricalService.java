package com.project.services;

import com.project.entities.Historical;
import java.util.List;

/**
 *
 * @author armdev
 */
public interface HistoricalService {

  
    public Long save(Historical entity);

    public List<Historical> findAll();
    
    public Historical findByHistoricalDate(String historicalDate);
}
