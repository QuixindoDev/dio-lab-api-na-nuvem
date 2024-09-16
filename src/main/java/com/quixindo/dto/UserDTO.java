package com.quixindo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotNull @NotBlank String name, @NotNull @NotBlank @Email String email, @NotNull @NotBlank String password) {
}
