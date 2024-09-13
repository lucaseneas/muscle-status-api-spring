package com.example.muscle_status_api.domain.exerciseLog;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RequestExerciseLog(@NotNull @Min(value = 1, message = "Value must be positive") Integer setNumber,
                                 @NotNull @Min(value = 1, message = "Value must be positive") Double weight,
                                 @NotNull @Min(value = 1, message = "Value must be positive") Integer repetition,
                                 String description) {
}
