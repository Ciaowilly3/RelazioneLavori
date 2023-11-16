package com.example.lavori.controllers;

import com.example.lavori.dto.UserRequestDto;
import com.example.lavori.models.User;
import com.example.lavori.services.Impl.UserServiceImpl;
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

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl){this.userServiceImpl = userServiceImpl;}

    @PostMapping
    public void  addUser(@RequestBody UserRequestDto userRequest){
        userServiceImpl.addUser(userRequest.getUserName(), userRequest.getLavoroName());
    }

    @GetMapping
    public List<User> getAllUsers(){return userServiceImpl.getAllUsers();}

    @GetMapping(path = "/{id}")
    public User getUserById (@PathVariable("id") Long id) {
        val user = userServiceImpl.getUserById(id);
        return user.orElse(null);
    }
    @PutMapping(path = "{id}")
    public  void updateUser(@PathVariable("id") Long id, @RequestBody User userToUpdate){
        userServiceImpl.updateUser(id, userToUpdate);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id")Long id){
        userServiceImpl.deleteUser(id);
    }

    @GetMapping(path = "name/{filters}")
    public String getUserByChar(@PathVariable("filters")
            @Pattern(regexp = "^[a-zA-Z]+$", message = "The filter has to contain only regular characters")
            @NotBlank(message = "The filter can't be blank or null")
                                    String filters){
        return userServiceImpl.retrieveSerializedNamesByUserName(filters);
    }

}
