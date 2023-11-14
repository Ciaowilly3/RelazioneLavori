package com.example.lavori.services;

import com.example.lavori.models.User;
import com.example.lavori.repositories.UserRepository;
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
class UserServiceImplTest {

    @Mock
    private UserRepository userRepositoryMock;
    @InjectMocks
    private UserServiceImpl userServiceImplTest;

    @Test
    void addUser() {
    }

    @Test
    void getAllUsers() {
        val user1 = new User("Michele", "c53537f0-8300-11ee-b962-0242ac120002");
        val user2 = new User("Marco", "fa4537f0-8300-11ee-b962-0242ac120002");

        when(userRepositoryMock.findAll()).thenReturn(List.of(user1, user2));

        val result = userServiceImplTest.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));

        verify(userRepositoryMock).findAll();
        verify(userRepositoryMock, times(1)).findAll();
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void retrieveSerializedNamesByUserName() {
    }

    @Test
    void retrieveSerializedNamesByUserNamev2() {
    }

    @Test
    void retrieveSerializedNamesByUserNamev3() {
    }
}