package com.example.muscle_status_api.domain.user;


import com.example.muscle_status_api.domain.workout.Workout;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestUser(Integer id, @NotBlank @NotNull String name, @Email @NotBlank @NotNull String email, @NotBlank @NotNull String password) {
}
