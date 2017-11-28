package com.kb.service.impl;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.kb.dao.UserDao;
import com.kb.model.User;
import com.kb.service.UserService;
 
@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserDao userDao;
 
    // Create of CRUD
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }
 
    // Read of CRUD
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
 
    // Read of CRUD
    @Transactional
    public User getUserById(int userid) {
        return userDao.getUserById(userid);
    }
 
    // Update of CRUD
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
 
    // Delete of CRUD
    @Transactional
    public void deleteUser(int userid) {
        userDao.deleteUser(userid);
    }
 
    public UserDao getUserDao() {
        return userDao;
    }
 
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
 
}
