package com.example.jobs.controllers;

import com.example.jobs.services.Impl.JobServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcExtensionsKt;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class JobControllerTest {

    @Mock
    private JobServiceImpl jobServiceImpl;

    @InjectMocks
    private  JobController jobController;

    private MockMvc mockMvc;
    @Test
    void addJob() {
    }

    @Test
    void getAllJobs() {
    }

    @Test
    void getJobById() {
    }

    @Test
    void updateJob() {
    }

    @Test
    void findByUserName() {
    }

    @Test
    void deleteJob() {
    }
}