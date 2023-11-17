package com.example.jobs.controllers;

import com.example.jobs.dto.JobDto;
import com.example.jobs.models.Job;
import com.example.jobs.services.Impl.JobServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcExtensionsKt;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(JobController.class)
@AutoConfigureMockMvc
class JobControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobServiceImpl jobServiceImpl;
    @Test
    void addJob() throws Exception{
        val job = Job.builder().jobName("muratore").build();

        mockMvc.perform(post("/api/job")
                .contentType("application/json")
                .content("{\"jobName\" : \"muratore\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(jobServiceImpl, times(1)).addJob(job);
    }

    @Test
    void getAllJobs() throws Exception {
        val job1 = Job.builder().jobName("muratore").build();
        val job2 = Job.builder().jobName("infermiere").build();
        val jobList = List.of(job2, job1);
        when(jobServiceImpl.getAllJobs()).thenReturn(jobList);

        mockMvc.perform(get("/api/job"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"jobName\" : \"muratore\"}, {\"jobName\" : \"infermiere\"}]"));
        verify(jobServiceImpl,times(1)).getAllJobs();
    }

    @Test
    void getJobById() throws Exception {
        val jobId = 12L;
        val job = Job.builder().jobName("muratore").build();

        when(jobServiceImpl.getJobById(jobId)).thenReturn(Optional.of(job));

        mockMvc.perform(get("/api/job/singleJob/{id}", jobId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"jobName\" : \"muratore\"}"));
        verify(jobServiceImpl, times(1)).getJobById(jobId);
    }

    @Test
    void getJobByIdNotFound() throws Exception{
        val jobId = 12L;

        when(jobServiceImpl.getJobById(jobId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/job/singleJob/{id}", jobId))
                .andExpect(status().isNotFound());
    }
    @Test
    void updateJob() throws Exception {
        val jobId = 12L;
        val job = Job.builder().jobName("muratore").build();

        mockMvc.perform(put("/api/job/{id}", jobId)
                .contentType("application/json")
                .content("{\"jobName\" : \"muratore\"}"))
                .andExpect(status().isOk());

        verify(jobServiceImpl, times(1)).updateJob(jobId, job);
    }

    @Test
    void findByUserName() throws Exception {
        val name = "Mario";
        val job = Job.builder().jobName("muratore").build();

        when(jobServiceImpl.findByUserName(name)).thenReturn(List.of(job));

        mockMvc.perform(get("/api/job/{name}", name))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"jobName\" : \"muratore\"}]"));

        verify(jobServiceImpl, times(1)).findByUserName(name);

    }

    @Test
    void deleteJob() throws Exception {
        val jobId = 12L;
        mockMvc.perform(delete("/api/job/{id}", jobId))
                .andExpect(status().isOk());
    }
}