package com.example.muscle_status_api.domain.workout;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RequestWorkout(@NotNull @NotBlank String name, String description) {
}
