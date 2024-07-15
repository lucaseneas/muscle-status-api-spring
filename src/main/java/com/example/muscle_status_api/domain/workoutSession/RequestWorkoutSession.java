package com.example.muscle_status_api.domain.workoutSession;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RequestWorkoutSession(@NotNull @NotBlank String name, String description) {
}
