package com.example.jobs.mappers;

import com.example.jobs.dto.JobDto;
import com.example.jobs.models.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JobAutoMapper {
    JobAutoMapper INSTANCE = Mappers.getMapper(JobAutoMapper.class);

    JobDto jobToJobDto(Job job);

    List<JobDto> jobsToJobsDtos(List<Job> jobs);
}