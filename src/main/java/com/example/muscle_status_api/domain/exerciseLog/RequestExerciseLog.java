package com.example.muscle_status_api.domain.exerciseLog;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RequestExerciseLog(@NotNull @NotBlank Integer setNumber ,@NotNull @NotBlank Double weight, @NotNull @NotBlank Integer repetition) {
}
