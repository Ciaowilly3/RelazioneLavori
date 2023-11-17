package com.example.jobs.controllers;

import com.example.jobs.dto.JobDto;
import com.example.jobs.exceptions.UsersByNameNotFoundException;
import com.example.jobs.mappers.JobAutoMapper;
//import com.example.jobs.mappers.JobMapper;
import com.example.jobs.models.Job;
import com.example.jobs.services.Impl.JobServiceImpl;
import lombok.val;
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
    public List<JobDto> getAllJobs(){return JobAutoMapper.INSTANCE.jobsToJobsDtos(jobServiceImpl.getAllJobs());}

    @GetMapping(path = "/singleJob/{id}")
    public JobDto getJobById(@PathVariable("id") Long id){
        val job = jobServiceImpl.getJobById(id);
        if (job.isEmpty()){
            throw new UsersByNameNotFoundException("Job not found with id:" + id);
        }
        return JobAutoMapper.INSTANCE.jobToJobDto(job.get());
    }
    @PutMapping(path = "/{id}")
    public void updateJob(@PathVariable("id") Long id,@RequestBody Job job){
        jobServiceImpl.updateJob(id, job);
    }

    @GetMapping(path = "/{name}")
    public List<JobDto> findByUserName(@PathVariable("name") String name){
        return JobAutoMapper.INSTANCE.jobsToJobsDtos(jobServiceImpl.findByUserName(name));
    }

    @DeleteMapping(path = "{id}")
    public void deleteJob(@PathVariable("id") Long id){
        jobServiceImpl.deleteJob(id);
    }
}
