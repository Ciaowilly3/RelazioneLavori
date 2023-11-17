package com.example.jobs.mappers;

import com.example.jobs.dto.JobDto;
import com.example.jobs.dto.UserDto;
import com.example.jobs.models.User;
import com.example.jobs.repositories.JobRepository;
import com.example.jobs.repositories.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
@Data
@RequiredArgsConstructor
public class UserMapper {
    //    @Autowired
//    private static UserRepository userRepository;
//    @Autowired
//    private static JobRepository jobRepository;
    public static List<UserDto> usersConverterToDtos(List<User> userList) {
        return userList.stream().map(UserMapper::userConvertererToDto).collect(Collectors.toList());
    }

    public static UserDto userConvertererToDto(User user) {
        return UserDto.builder().userName(user.getName()).jobDto(JobDto.builder().jobName(user.getJob().getJobName()).build()).build();
    }
}

//    public static List<User> usersDtosConverterToUsersList(List<UserDto> userDtoList){
//        return userDtoList.stream().map(UserMapper::userDtoConverterToUser).collect(Collectors.toList());
//    }
//    public static User userDtoConverterToUser(UserDto userDto){
//
//        return User.builder().name(userDto.getUserName()).userId(userRepository.findUserIdByUserName(userDto.getUserName())).job(jobRepository.findFirstJobByJobName(userDto.getJobDto().getJobName())).build();
//    }
//}
