package com.security.security.controllers;

import com.security.security.entity.Items;
import com.security.security.entity.User;
import com.security.security.service.UserService;
import com.security.security.utils.UniversalRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public")
public class userController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register-user")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/user-by-username")
    public User userByUsernameOrEmail(@RequestBody Map<String, String> payload){
        String username = payload.get("usernameOrEmail");
        return userService.getUserByUsernameOrEmail(username);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginCred){
        String usernameOrEmail = loginCred.get("usernameOrEmail");
        String password = loginCred.get("password");
        return userService.verifyUser(usernameOrEmail, password);
    }

    @PostMapping("/get-int")
    public Integer getInt(@RequestBody UniversalRequestHandler universalRequestHandler){
        List<User> myList = universalRequestHandler.getListValue("myList", User.class);
        return universalRequestHandler.getIntegerValue("myInt");
//      new bug
    }

}
