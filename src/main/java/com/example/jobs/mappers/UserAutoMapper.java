package com.example.jobs.mappers;

import com.example.jobs.dto.JobDto;
import com.example.jobs.dto.UserDto;
import com.example.jobs.models.Job;
import com.example.jobs.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserAutoMapper {
    UserAutoMapper INSTANCE = Mappers.getMapper(UserAutoMapper.class);

    @Mapping(source = "job", target = "jobDto")
    @Mapping(target = "userName", expression = "java(user.getUserName())")
    UserDto userToUserDto(User user);

    List<UserDto> usersToUserDtos(List<User> users);

}
