package com.user.service;

import com.user.dto.UserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.entity.User;
import com.user.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto registerUser(UserRequestDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }


        User user = new User();

        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());

        //Default Role
        user.setRole("USER");
        User saveUser = userRepository.save(user);

        return convertToResponse(saveUser);
    }

    private UserResponseDto convertToResponse(User user) {

        UserResponseDto response = new UserResponseDto();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        return response;
    }
}
