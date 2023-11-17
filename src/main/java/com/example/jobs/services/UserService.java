package com.example.jobs.services;

import com.example.jobs.dto.UserDto;
import com.example.jobs.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public void addUser(String userName, String jobName);

    public List<User> getAllUsers();

    public void updateUser(Long idToUpdate, User user);

    public void deleteUser(Long idToDelete);

    public Optional<User> getUserById(Long id);

    public String retrieveSerializedNamesByUserName(String searchKey);

    public String retrieveSerializedNamesByUserNamev2(String searchKey);

    public String retrieveSerializedNamesByUserNamev3(String searchKey);
}
