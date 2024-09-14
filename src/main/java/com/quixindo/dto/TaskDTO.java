package com.quixindo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskDTO(@NotBlank @NotNull String description, @NotNull boolean status) {
}
