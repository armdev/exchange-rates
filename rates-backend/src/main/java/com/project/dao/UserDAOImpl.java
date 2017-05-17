package com.project.dao;

import com.project.entities.User;
import com.project.utils.HashUtils;
import java.util.Date;
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
@Repository("userDAO")
@Qualifier("userDAO")
@Transactional(propagation = Propagation.REQUIRED)
public class UserDAOImpl extends AbstractDao implements UserDAO {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl() {
        LOG.info("UserDAOImpl called");
    }

    @Override
    public User userLogin(String email, String password) {
        User entity = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.email=:email AND c.passwd=:passwd")
                    .setParameter("email", email)
                    .setParameter("passwd", HashUtils.hashPassword(password));
            if (query.uniqueResult() != null) {
                entity = (User) query.uniqueResult();
            }
        } catch (Exception e) {
            LOG.info("Exception in user login " + e.getLocalizedMessage());
        }
        return entity;
    }

    @Override
    public Long save(User entity) {
        Date currentDate = new Date(System.currentTimeMillis());
        Long id = 0L;
        try {
            if (entity.getPasswd() != null) {
                String hashedPasswd = HashUtils.hashPassword(entity.getPasswd());
                entity.setRegisterDate(currentDate);
                entity.setPasswd(hashedPasswd);
                getSession().persist(entity);
                getSession().flush();
                id = entity.getId();
            }
        } catch (HibernateException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return id;
    }

    @Override
    public Long update(User entity) {
        Long id = 0L;
        try {
            getSession().merge(entity);
            getSession().flush();
            id = entity.getId();
        } catch (HibernateException e) {
            LOG.error(e.getLocalizedMessage());
        }
        return id;
    }

    @Override
    public User findUser(Long id) {
        User entity = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.id=:id").setParameter("id", id);
            entity = (User) query.uniqueResult();
            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            LOG.info("getLocalizedMessage " + e.getLocalizedMessage());
            return null;
        }
        return entity;
    }

    @Override
    public User getByEmail(String email) {
        User entity = null;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.email=:email").setParameter("email", email);
            entity = (User) query.uniqueResult();
            if (entity == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return entity;
    }

    @Override
    public boolean checkUserEmailForUpdate(Long id, String email) {
        boolean retValue = false;
        try {
            Query query = getSession().createQuery("SELECT c FROM User c WHERE c.email=:email and c.id != :id").setParameter("email", email).setParameter("id", id);
            User entity = (User) query.uniqueResult();
            if (entity != null) {
                retValue = true;
            }
        } catch (Exception e) {
        }
        return retValue;
    }

    @Override
    public int updatePassword(Long userId, String password) {
        int executeUpdate = 0;
        if (userId != null && password != null) {
            LOG.info("######userId " + userId);
            LOG.info("######password " + password);
            User user = this.findUser(userId);
            LOG.info("######user find " + user);
            if (user != null && user.getId().equals(userId)) {
                try {
                    executeUpdate = getSession().createQuery("UPDATE User o SET o.passwd=:passwd WHERE o.id=:id")
                            .setParameter("passwd", HashUtils.hashPassword(password.trim()))
                            .setParameter("id", userId)
                            .executeUpdate();
                    LOG.info("######executeUpdate " + executeUpdate);
                } catch (Exception e) {
                    LOG.info("getLocalizedMessage " + e.getLocalizedMessage());
                }
            }
        }
        LOG.info("######executeUpdate returned " + executeUpdate);
        return executeUpdate;
    }

    private boolean delete(Long id) {
        try {
            User findUser = this.findUser(id);
            if (findUser != null) {
                this.delete(findUser);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

}
