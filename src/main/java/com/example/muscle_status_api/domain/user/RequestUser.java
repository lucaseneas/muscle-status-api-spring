package com.example.muscle_status_api.domain.user;


import com.example.muscle_status_api.domain.workout.Workout;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RequestUser(Integer id, @NotBlank @NotNull String name, @NotBlank @NotNull String email, @NotBlank @NotNull String password) {
}
