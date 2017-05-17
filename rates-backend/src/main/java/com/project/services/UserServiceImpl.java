package com.project.services;

import com.project.dao.UserDAO;
import com.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author armdev
 */
@Service("userService")
@Qualifier("userService")
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;

    @Override
    public Long save(User entity) {
        return dao.save(entity);
    }
    
    @Override
    public Long update(User entity) {
        return dao.update(entity);
    }

    @Override
    public User findUser(Long id) {
        return dao.findUser(id);
    }

    @Override
    public User getByEmail(String email) {
        return dao.getByEmail(email);
    }

    @Override
    public User userLogin(String email, String password) {
        return dao.userLogin(email, password);
    }

    @Override
    public int updatePassword(Long userId, String password) {
        return dao.updatePassword(userId, password);
    }

    @Override
    public boolean checkUserEmailForUpdate(Long id, String email) {
        return dao.checkUserEmailForUpdate(id, email);
    }


}
