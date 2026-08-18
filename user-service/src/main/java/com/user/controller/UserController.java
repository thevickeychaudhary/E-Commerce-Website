package com.user.controller;

import com.user.dto.UserRequestDto;
import com.user.dto.UserResponseDto;
import com.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDto registerUser(@Valid @RequestBody UserRequestDto dto) {
        return userService.registerUser(dto);
    }
}
