package com.example.technicaltest.infrastructure.controller.dto;

import com.example.technicaltest.domain.model.User;

public record UserDTO(String userId) {
    public static User toModel(UserDTO userDTO) {
        return new User(userDTO.userId);
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.userId());
    }
}
