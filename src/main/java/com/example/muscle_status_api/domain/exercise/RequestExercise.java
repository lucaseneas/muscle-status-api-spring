package com.example.muscle_status_api.domain.exercise;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RequestExercise(@NotBlank @NotNull String name, String description, @NotBlank @NotNull String muscle_group) {
}
