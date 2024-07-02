package com.example.muscle_status_api.domain.user;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record RequestUser(Long id,@NotBlank @NotNull String name, @NotBlank @NotNull String email, @NotBlank @NotNull String password) {
}
