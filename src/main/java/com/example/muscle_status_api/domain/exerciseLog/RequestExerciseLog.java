package com.example.muscle_status_api.domain.exerciseLog;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestExerciseLog(@NotNull @NotBlank Integer setNumber ,@NotNull @NotBlank Double weight, @NotNull @NotBlank Integer repetition) {
}
