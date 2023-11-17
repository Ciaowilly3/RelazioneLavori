//package com.example.jobs.mappers;
//
//import com.example.jobs.dto.JobDto;
//import com.example.jobs.models.Job;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class JobMapper {
//
//    public static List<JobDto> jobsConverterToDtos(List<Job> jobList){
//        return jobList.stream().map(JobMapper::jobConverterToDto).collect(Collectors.toList());
//    }
//    public static JobDto jobConverterToDto(Job job){
//        return JobDto.builder().jobName(job.getJobName()).build();
//    }
//}
