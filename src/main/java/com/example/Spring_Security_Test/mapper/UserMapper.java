package com.example.Spring_Security_Test.mapper;

import com.example.Spring_Security_Test.dao.entity.User;
import com.example.Spring_Security_Test.dto.request.UserRequest;
import com.example.Spring_Security_Test.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class UserMapper {

    public static UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }

    public static User userRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .build();
    }
}
