package com.example.userservice.mapper;

import com.example.userservice.dto.UserRequestDTO;
import com.example.userservice.dto.UserResponseDTO;
import com.example.userservice.model.User;

public class UserMapper  {
    public static UserResponseDTO toDTO(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public static User toEntity(UserRequestDTO dto){
        return User.builder()
        .username(dto.getUsername())
        .email(dto.getEmail())
        .role(dto.getRole())
        .password(dto.getPassword())
        .build();
    }
}
