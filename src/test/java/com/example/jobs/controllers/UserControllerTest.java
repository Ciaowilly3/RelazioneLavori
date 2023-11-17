package com.example.jobs.controllers;

import com.example.jobs.dto.UserRequestDto;
import com.example.jobs.mappers.UserMapper;
import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import com.example.jobs.services.Impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserControllerTest {

    @Mock
    private UserMapper userMapper;
    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;
    @Test
    void addUser() {
        val user = User.builder().userId(12L).name("Marco").build();

        val userRequest = new UserRequestDto();
        userRequest.setUserName("Marco");
        userRequest.setUserId(12L);

        userController.addUser(userRequest);

        when(userServiceImpl.getUserById(12L)).thenReturn(Optional.of(user));

        var gottenUser = userServiceImpl.getUserById(12L);

        if(gottenUser.isEmpty()){
            throw new RuntimeException("User not found");
        }

        assertNotNull(gottenUser.get());

        assertEquals(user, gottenUser.get());
        verify(userServiceImpl, times(1)).addUser("Marco", null);
    }

    @Test
    void getAllUsers() {
        val job = Job.builder().jobName("muratore").build();
        val user1 = User.builder().userId(12L).name("Marco").job(job).build();
        val user2 = User.builder().userId(122L).name("Michele").job(job).build();

        when(userServiceImpl.getAllUsers()).thenReturn(List.of(user1, user2));

        val result = userController.getAllUsers();

        val expectedUsers = UserMapper.usersConverterToDtos(List.of(user1, user2));
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedUsers, result);

        verify(userServiceImpl, times(1)).getAllUsers();
    }

    @Test
    void getUserById() {
        val userId = 12L;
        val job = Job.builder().jobName("muratore").build();
        val user = User.builder().userId(12L).name("Marco").job(job).build();

        when(userServiceImpl.getUserById(userId)).thenReturn(Optional.of(user));

        val result = userController.getUserById(userId);

        val expectedUser = UserMapper.userConvertererToDto(user);

        assertEquals(expectedUser, result);

        verify(userServiceImpl, times(1)).getUserById(userId);
    }

    @Test
    void updateUser() {
        val userId = 12L;

        val user = User.builder().userId(userId).name("Marco").build();

        userController.updateUser(userId, user);

        verify(userServiceImpl, times(1)).updateUser(userId, user);

    }

    @Test
    void deleteUser() {
        val userId = 12L;

       doNothing().when(userServiceImpl).deleteUser(userId);

        userController.deleteUser(userId);

        verify(userServiceImpl, times(1)).deleteUser(userId);
    }

    @Test
    void getUserByChar() {

        val exampleFilter = "Lu";

        val user = User.builder().userId(12L).name("Luca").build();

        when(userServiceImpl.retrieveSerializedNamesByUserName(exampleFilter)).thenReturn(user.getName());

        val result = userController.getUserByChar(exampleFilter);

        assertEquals(user.getName(), result);
        verify(userServiceImpl, times(1)).retrieveSerializedNamesByUserName(exampleFilter);

    }
}