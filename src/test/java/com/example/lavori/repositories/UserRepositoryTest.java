package com.example.lavori.repositories;

import com.example.lavori.models.User;
import com.example.lavori.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Mock
    private UserServiceImpl userServiceImplMock;
    @Mock
    private UserRepository userRepositoryTest;
    @Test
    void findByName() {

        val user = new User("Marco","fa4537f0-8300-11ee-b962-0242ac12000");

        Mockito.when(userRepositoryTest.findByName("Marco")).thenReturn(List.of(user));

        val result = userRepositoryTest.findByName("Marco");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    void findByLavoro() {
    }

    @Test
    void findByNameStartingWith() {
    }

    @Test
    void findNamesByNameStartingWith() {
    }
}