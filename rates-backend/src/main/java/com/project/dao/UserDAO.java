package com.project.dao;

import com.project.entities.User;

/**
 *
 * @author armdev
 */
public interface UserDAO {

    public User userLogin(String email, String password);

    public Long save(User entity);

    public User findUser(Long id);

    public User getByEmail(String email);

    public boolean checkUserEmailForUpdate(Long id, String email);

    public int updatePassword(Long userId, String password);   

    public Long update(User entity);

}
