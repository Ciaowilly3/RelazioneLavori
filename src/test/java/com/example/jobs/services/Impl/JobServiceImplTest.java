package com.example.jobs.services.Impl;

import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import com.example.jobs.repositories.JobRepository;
import com.example.jobs.repositories.UserRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private JobServiceImpl jobServiceImpl;
    @Test
    void addJob() {
        val jobId = 12L;
        val job = Job.builder().jobName("muratore").jobId(jobId).build();

        jobServiceImpl.addJob(job);

        Mockito.verify(jobRepository, Mockito.times(1)).save(job);
    }

    @Test
    void getAllJobs() {
        val jobId = 12L;
        val job1 = Job.builder().jobName("muratore").jobId(jobId).build();
        val job2 = Job.builder().jobName("infermiere").jobId(jobId + 1).build();
        val expetedList = List.of(job1, job2);

        Mockito.when(jobRepository.findAll()).thenReturn(expetedList);

        val result = jobServiceImpl.getAllJobs();

        Mockito.verify(jobRepository, Mockito.times(1)).findAll();
        assertEquals(expetedList, result);

    }

    @Test
    void getJobById() {
        val jobId = 12L;

        val job = Job.builder().jobName("muratore").jobId(jobId).build();

        Mockito.when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));

        val result = jobServiceImpl.getJobById(jobId);

        if (result.isEmpty()){
            throw new RuntimeException("Job not found");
        }

        assertEquals(job, result.get());
        Mockito.verify(jobRepository, Mockito.times(1)).findById(jobId);
    }

    @Test
    void updateJob() {
        val jobIdToUpdate = 12L;

        val jobToUpdate = Job.builder().jobName("muratore").jobId(jobIdToUpdate).build();
        val newJob = Job.builder().jobName("macellaio").jobId(jobIdToUpdate).build();

        Mockito.when(jobRepository.findById(jobIdToUpdate)).thenReturn(Optional.of(jobToUpdate));

        jobServiceImpl.updateJob(jobIdToUpdate, newJob);

        Mockito.verify(jobRepository, Mockito.times(1)).save(newJob);

    }

    @Test
    void deleteJob() {
        val jobIdToDelete = 12L;

        val jobToDelete = Job.builder().jobName("muratore").jobId(jobIdToDelete).build();

        Mockito.when(jobRepository.findById(jobIdToDelete)).thenReturn(Optional.of(jobToDelete));

        jobServiceImpl.deleteJob(jobIdToDelete);

        Mockito.verify(jobRepository, Mockito.times(1)).delete(jobToDelete);
    }

    @Test
    void findByUserName() {
        val jobId = 12L;
        val job = Job.builder().jobName("muratore").jobId(jobId).build();
        val name = "Marco";
        val user = User.builder().userId(1L).name(name).job(job).build();

        Mockito.when(userRepository.findByName(name)).thenReturn(List.of(user));

        val result = jobServiceImpl.findByUserName(name);

        assertEquals(job, result.get(0));
    }
}