package com.kb.dao;
 
import java.util.List;
 
import com.kb.model.User;
 
public interface UserDao {
    
     public void addUser(User user);
     
     public List<User> getAllUsers();
     
     public User getUserById(int userid);
     
     public void updateUser(User user);
     
     public void deleteUser(int userid);
 
}
