package com.example.jobs.repositories;

import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import com.example.jobs.services.Impl.JobServiceImpl;
import com.example.jobs.services.Impl.UserServiceImpl;
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
    private JobServiceImpl jobServiceImpl;
    @InjectMocks
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
    void findByjob() {
        val job = Job.builder().jobId(1L).jobName("dentista").build();

        val user = User.builder().name("Marco").userId(23L).build();

        when(userRepository.findByJob(job)).thenReturn(List.of(user));

        val result = userRepository.findByJob(job);

        Mockito.verify(userRepository, Mockito.times(1)).findByJob(job);

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