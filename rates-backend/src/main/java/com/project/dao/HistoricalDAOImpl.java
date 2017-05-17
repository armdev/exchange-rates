package com.project.dao;

import com.project.entities.Currency;
import com.project.entities.Historical;
import com.project.entities.User;
import com.project.utils.HashUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
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
@Repository("historicalDAO")
@Qualifier("historicalDAO")
public class HistoricalDAOImpl extends AbstractDao implements HistoricalDAO {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(HistoricalDAOImpl.class);

    public HistoricalDAOImpl() {
        LOG.info("HistoricalDAOImpl called");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long save(Historical entity) {
        Long id = 0L;
        try {
            getSession().persist(entity);
            getSession().flush();
            id = entity.getId();
        } catch (HibernateException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Historical findByHistoricalDate(String historicalDate) {
        Historical entity = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM Historical c WHERE c.historicalDate=:historicalDate").setParameter("historicalDate", historicalDate);
            entity = (Historical) query.uniqueResult();
            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Historical> findAll() {
        List<Historical> list = new ArrayList<>();
        try {
            Query query = getSession().createQuery("SELECT c FROM Historical c ORDER BY c.id DESC").setMaxResults(10);
            list = query.list();
        } catch (Exception e) {
            LOG.info("Error occured in get list");
        }
        return list;
    }
}
