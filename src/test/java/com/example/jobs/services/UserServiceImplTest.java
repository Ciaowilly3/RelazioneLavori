package com.example.jobs.services;

import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import com.example.jobs.repositories.JobRepository;
import com.example.jobs.repositories.UserRepository;
import com.example.jobs.services.Impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private JobRepository jobRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void addUser() {

        val job = Job.builder().jobId(12L).jobName("Medico").build();

        val userName = "Marco";

        userServiceImpl.addUser(userName, job.getJobName());

//        when(jobRepository.findFirstByjobNameOrderByjobIdAsc(job.getjobName())).thenReturn(Optional.of(job));

        ArgumentCaptor<User> result = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(result.capture());

        verify(userRepository, times(1)).save(result.getValue());

    }

    @Test
    void getAllUsers() {
        val user1 = User.builder().userName("Marco").userId(23L).build();
        val user2 = User.builder().userName("Luca").userId(324L).build();

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        val result = userServiceImpl.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void updateUser() {
        val userId = 213L;

        val user = User.builder().userName("Marco").userId(userId).build();

        val newUser = User.builder().userName("Lorenzo").build();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userServiceImpl.updateUser(userId, newUser);

        val result = userRepository.findById(userId);

        if (result.isEmpty()){
            throw new RuntimeException("User Not Found");
        }

        assertNotEquals(newUser, result.get());
        newUser.setUserId(userId);
        verify(userRepository, times(1)).save(newUser);


    }

    @Test
    void deleteUser() {
        val userId = 12L;

        userServiceImpl.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void getUserById() {
        val userId = 12L;

        val user = User.builder().userName("Marco").userId(userId).build();

        when(userServiceImpl.getUserById(userId)).thenReturn(Optional.of(user));
        val result = userServiceImpl.getUserById(12L);

        if (result.isEmpty()){
            throw new RuntimeException("User Not Found");
        }
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void retrieveSerializedNamesByUserName() {
        val chars = "lu";

        val user = User.builder().userName("Luca").userId(12L).build();

        when(userRepository.findByUserNameStartingWith(chars)).thenReturn(List.of(user));

        val resultString = userServiceImpl.retrieveSerializedNamesByUserName(chars);


        assertNotNull(resultString);
        assertEquals(resultString, user.getUserName());

    }

    @Test
    void retrieveSerializedNamesByUserNamev2() {

        val chars = "lu";

        val user = User.builder().userName("Luca").userId(12L).build();

        when(userRepository.findUserNamesByNameStartingWith(chars)).thenReturn(List.of(user.getUserName()));

        val resultString = userServiceImpl.retrieveSerializedNamesByUserNamev2(chars);


        assertNotNull(resultString);
        assertEquals(resultString, user.getUserName());
    }

    @Test
    void retrieveSerializedNamesByUserNamev3() {

        val chars = "lu";

        val user = User.builder().userName("Luca").userId(12L).build();

        when(userRepository.findUserNamesByNameStartingWith(chars)).thenReturn(List.of(user.getUserName()));

        val resultString = userServiceImpl.retrieveSerializedNamesByUserNamev3(chars);


        assertNotNull(resultString);
        assertEquals(resultString, user.getUserName());
    }
}