package com.example.jobs.services.Impl;

import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import com.example.jobs.repositories.JobRepository;
import com.example.jobs.repositories.UserRepository;
import com.example.jobs.services.JobService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

    private final UserRepository userRepository;

    private final JobRepository jobRepository;
    @Autowired
    public JobServiceImpl(JobRepository jobRepository, UserRepository userRepository){
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public void addJob(Job job){
        log.info("Start - addjob - args: job= {}", job);
        log.info("Start - addjob - out:{}", job);
        jobRepository.save(job);
    }

    public List<Job> getAllJobs(){
        log.info("Start - getAlljobs - args: none");
        val alljobs = jobRepository.findAll();
        log.info("End - getAlljobs - out: {}", alljobs);
        return alljobs;
    }

    public Optional<Job> getJobById(Long id){
        log.info("Start - getjobById - args: id={}", id);
        val job = jobRepository.findById(id);
        log.info("End - getjobById - out: {}", job);
        return job;
    }

    public void updateJob(Long idToUpdate, Job job){
        log.info("Start - updatejob - args: id e job {} {}", idToUpdate, job);
        Optional<Job> jobToUpdate = jobRepository.findById(idToUpdate);
        jobToUpdate
                .map(l -> {
                    l.setJobName(job.getJobName());
                    jobRepository.save(l);
                    log.info("End - updatejob - out: {}", l);
                    return l;
                });
    }

    public void deleteJob(Long id){
        log.info("Start - deletejob - args: id={}", id);
        val jobToDelete = jobRepository.findById(id);
        if (jobToDelete.isEmpty()){
            return;
        }
        val job = jobToDelete.get();
        val usersTodelete = userRepository.findByJob(job);
        usersTodelete.forEach(userRepository::delete);
        jobRepository.delete(job);
        log.info("End - deletejob - out: {}", job);
    }

    public List<Job> findByUserName(String name){
        log.info("Start - findByUserName - args: name={}", name);
        val userList = userRepository.findByName(name);
        val user = userList.stream().map(User::getJob).toList();
        log.info("End - findByUserName - out: {}", user);
        return user;
    }
}
