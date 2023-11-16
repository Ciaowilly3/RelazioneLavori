package com.example.jobs.controllers;

import com.example.jobs.models.Job;
import com.example.jobs.services.Impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/job")
@RestController
public class JobController {

    private final JobServiceImpl jobServiceImpl;
    @Autowired
    public JobController(JobServiceImpl jobServiceImpl){this.jobServiceImpl = jobServiceImpl;}

    @PostMapping
    public void addJob(@RequestBody Job job){jobServiceImpl.addJob(job);}

    @GetMapping
    public List<Job> getAllJobs(){return jobServiceImpl.getAllJobs();}

    @GetMapping(path = "/singleJob/{id}")
    public Job getJobById(@PathVariable("id") Long id){
        return jobServiceImpl.getJobById(id)
                .orElse(null);
    }
    @PutMapping(path = "/{id}")
    public void updateJob(@PathVariable("id") Long id,@RequestBody Job job){
        jobServiceImpl.updateJob(id, job);
    }

    @GetMapping(path = "/{name}")
    public List<Job> findByUserName(@PathVariable("name") String name){
        return jobServiceImpl.findByUserName(name);
    }

    @DeleteMapping(path = "{id}")
    public void deleteJob(@PathVariable("id") Long id){
        jobServiceImpl.deleteJob(id);
    }
}
