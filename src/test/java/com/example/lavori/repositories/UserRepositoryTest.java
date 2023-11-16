package com.example.lavori.repositories;

import com.example.lavori.models.Lavoro;
import com.example.lavori.models.User;
import com.example.lavori.services.Impl.LavoroServiceImpl;
import com.example.lavori.services.Impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @InjectMocks
    private LavoroServiceImpl lavoroServiceImpl;
    @Mock
    private UserRepository userRepository;
    @Test
    void findByName() {

        val user = User.builder().name("Marco").userId(23L).build();

        when(userRepository.findByName("Marco")).thenReturn(List.of(user));

        val result = userRepository.findByName("Marco");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(user, result.get(0));
    }

    @Test
    void findByLavoro() {
        val lavoro = Lavoro.builder().lavoroId(1L).lavoroName("dentista").build();

        val user = User.builder().name("Marco").userId(23L).build();

        when(userRepository.findByLavoro(lavoro)).thenReturn(List.of(user));

        val result = userRepository.findByLavoro(lavoro);

        Mockito.verify(userRepository, Mockito.times(1)).findByLavoro(lavoro);

        assertEquals(user, result.get(0));
    }

    @Test
    void findByNameStartingWith() {
        val startingChars = "lu";

        userRepository.findByNameStartingWith(startingChars);

        verify(userRepository, times(1)).findByNameStartingWith(startingChars);
    }

    @Test
    void findNamesByNameStartingWith() {
        val startingChars = "lu";

        userRepository.findByNameStartingWith(startingChars);

        verify(userRepository, times(1)).findByNameStartingWith(startingChars);
    }
}