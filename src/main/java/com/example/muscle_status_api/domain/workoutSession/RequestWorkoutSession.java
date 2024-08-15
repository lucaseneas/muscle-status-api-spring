package com.example.muscle_status_api.domain.workoutSession;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestWorkoutSession(@NotNull @NotBlank String name, String description) {
}
