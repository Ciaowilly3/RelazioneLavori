package com.example.Lavori.controllers;

import com.example.Lavori.models.User;
import com.example.Lavori.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/user")
@RestController
public class UserController {

    public static class UserRequest{
        private String userName;
        private String lavoroName;

        public String getUserName() {
            return userName;
        }

        public String getLavoroName() {
            return lavoroName;
        }
    }
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl){this.userServiceImpl = userServiceImpl;}

    @PostMapping
    public void  addUser(@RequestBody UserRequest userRequest){
        userServiceImpl.addUser(userRequest.getUserName(), userRequest.getLavoroName());
    }

    @GetMapping
    public List<User> getAllUsers(){return userServiceImpl.getAllUsers();}
}
