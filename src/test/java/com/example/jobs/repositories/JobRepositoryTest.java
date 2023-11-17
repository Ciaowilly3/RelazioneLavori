package com.example.jobs.repositories;

import com.example.jobs.models.Job;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JobRepositoryTest {
    @Mock
    private JobRepository jobRepository;
    @Test
    void findFirstByJobNameOrderByJobIdAsc() {
        val job = Job.builder().jobId(12L).jobName("muratore").build();

        Mockito.when(jobRepository.findFirstByJobNameOrderByJobIdAsc(job.getJobName())).thenReturn(Optional.of(job));

        val foundJob = jobRepository.findFirstByJobNameOrderByJobIdAsc(job.getJobName());

        if (foundJob.isEmpty()){
            throw new RuntimeException("Job not found");
        }

        assertEquals(job, foundJob.get());
        Mockito.verify(jobRepository, Mockito.times(1)).findFirstByJobNameOrderByJobIdAsc("muratore");
    }

}