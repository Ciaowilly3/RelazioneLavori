package com.example.lavori.controllers;

import com.example.lavori.exceptions.UsersByNameNotFoundException;
import com.example.lavori.models.User;
import com.example.lavori.services.UserServiceImpl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Getter
    public static class UserRequest{
        private String userName;
        private String lavoroName;

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
        val user = userServiceImpl.getUserById(id);
        return user.orElse(null);
        // TODO: Controller advise nel configuration
    }
    @PutMapping(path = "{id}")
    public  void updateUser(@PathVariable("id") String id, @RequestBody User userToUpdate){
        userServiceImpl.updateUser(id, userToUpdate);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id")String id){
        userServiceImpl.deleteUser(id);
    }

    @GetMapping(path = "name/{filters}")
    public String getUserByChar(@PathVariable("filters")
            @Pattern(regexp = "^[a-zA-Z]+$", message = "il parametro deve contenere solo caratteri dell'alfabeto internazionale")
            @NotBlank(message = "il parametro non deve essere blank o null")
                                    String filters){
        return userServiceImpl.retrieveSerializedNamesByUserName(filters);
    }
}
