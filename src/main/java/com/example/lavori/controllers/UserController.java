package com.example.lavori.controllers;

import com.example.lavori.models.User;
import com.example.lavori.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
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

    @GetMapping(path = "/{id}")
    public User getUserById (@PathVariable("id") String id) {
        return userServiceImpl.getUserById(id)
                .orElse(null);
    }
    @PutMapping(path = "{id}")
    public  void updateUser(@PathVariable("id") String id, @RequestBody User userToUpdate){
        userServiceImpl.updateUser(id, userToUpdate);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id")String id){
        userServiceImpl.deleteUser(id);
    }
}
