package com.example.lavori.controllers;

import com.example.lavori.models.User;
import com.example.lavori.services.Impl.UserServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userServiceImplMock;

    @InjectMocks
    private UserControllerTest userControllerTest;
    @Test
    void addUser() {
    }

    @Test
    void getAllUsers() {
        val user1 = new User("Michele", "c53537f0-8300-11ee-b962-0242ac120002");
        val user2 = new User("Marco", "fa4537f0-8300-11ee-b962-0242ac120002");

        when(userServiceImplMock.getAllUsers()).thenReturn(List.of(user1, user2));

        val result = userServiceImplMock.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));

        verify(userServiceImplMock, times(1)).getAllUsers();
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserByChar() {
    }
}