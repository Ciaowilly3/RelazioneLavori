package com.example.jobs.controllers;

import com.example.jobs.dto.UserDto;
import com.example.jobs.dto.UserRequestDto;
import com.example.jobs.exceptions.UsersByNameNotFoundException;
import com.example.jobs.mappers.UserAutoMapper;
//import com.example.jobs.mappers.UserMapper;
import com.example.jobs.models.User;
import com.example.jobs.services.Impl.UserServiceImpl;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
        userServiceImpl.addUser(userRequest.getUserName(), userRequest.getJobName());
    }

    @GetMapping
    public List<UserDto> getAllUsers(){return UserAutoMapper.INSTANCE.usersToUserDtos(userServiceImpl.getAllUsers());}

    @GetMapping(path = "/{id}")
    public UserDto getUserById (@PathVariable("id") Long id) {
        val user = userServiceImpl.getUserById(id);
        if (user.isEmpty()){
            throw new UsersByNameNotFoundException("User not found with id: " + id.toString());
        }
        return UserAutoMapper.INSTANCE.userToUserDto(user.get());
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
