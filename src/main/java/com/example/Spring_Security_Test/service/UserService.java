package com.example.Spring_Security_Test.service;

import com.example.Spring_Security_Test.dao.entity.User;
import com.example.Spring_Security_Test.dao.repository.UserRepository;
import com.example.Spring_Security_Test.dto.response.UserResponse;
import com.example.Spring_Security_Test.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::userToUserResponse)
                .collect(Collectors.toList());
    }
}
