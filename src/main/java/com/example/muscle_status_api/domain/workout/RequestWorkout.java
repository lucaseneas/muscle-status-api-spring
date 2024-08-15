package com.example.muscle_status_api.domain.workout;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RequestWorkout(@NotNull @NotBlank @NotEmpty String name, String description) {
}
