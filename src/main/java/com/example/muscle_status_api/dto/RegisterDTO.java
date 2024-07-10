package com.example.muscle_status_api.dto;


import com.example.muscle_status_api.domain.user.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
