package com.kb.controller;
 
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.kb.model.User;
import com.kb.service.UserService;
 
@Controller
public class UserController {
 
    @Autowired
    private UserService userService;
 
    @RequestMapping("/home")
    public String listBooks(Map<String, Object> map) {
        map.put("user", new User());
        map.put("userList", userService.getAllUsers());
        return "user";
    }
 
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        if (null != user) {
            userService.addUser(user);
        }
        return "redirect:/home";
    }
 
    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
        return "redirect:/home";
    }
 
    @RequestMapping("/edit/{userId}")
    public String editUser(@PathVariable("userId") int userId, Map<String, Object> map) {
        User user = userService.getUserById(userId);
        // Providing new value for Edit
        user.setFirstName("EditedName");
        userService.updateUser(user);
        map.put("userList", userService.getAllUsers());
        return "redirect:/home";
    }
 
    public UserService getUserService() {
        return userService;
    }
 
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
 
}
