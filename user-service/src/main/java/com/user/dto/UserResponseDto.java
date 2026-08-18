package com.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
}
