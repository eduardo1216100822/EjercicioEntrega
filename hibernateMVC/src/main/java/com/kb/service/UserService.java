package com.kb.service;
 
import java.util.List;
 
import com.kb.model.User;
 
public interface UserService {
    
    public void addUser(User user);
 
    public List<User> getAllUsers();
 
    public User getUserById(int userid);
 
    public void updateUser(User user);
 
    public void deleteUser(int userid);
 
}
