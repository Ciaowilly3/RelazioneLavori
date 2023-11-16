package com.example.jobs.repositories;

import com.example.jobs.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findFirstByJobNameOrderByJobIdAsc(String jobName);
}
