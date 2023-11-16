package com.example.jobs.services;

import com.example.jobs.models.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    public void addJob(Job job);

    public List<Job> getAllJobs();

    public Optional<Job> getJobById(Long id);

    public void updateJob(Long idToUpdate, Job job);

    public void deleteJob(Long id);

    public List<Job> findByUserName(String name);
}
