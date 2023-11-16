package com.example.jobs.controllers;

import com.example.jobs.dto.UserRequestDto;
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
        val user1 = User.builder().userId(12L).name("Marco").build();
        val user2 = User.builder().userId(122L).name("Michele").build();

        when(userServiceImpl.getAllUsers()).thenReturn(List.of(user1, user2));

        val result = userController.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));

        verify(userServiceImpl, times(1)).getAllUsers();
    }

    @Test
    void getUserById() {
        val userId = 12L;
        val user = User.builder().userId(12L).name("Marco").build();

        when(userServiceImpl.getUserById(userId)).thenReturn(Optional.of(user));

        val result = userController.getUserById(userId);

        assertEquals(user, result);

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