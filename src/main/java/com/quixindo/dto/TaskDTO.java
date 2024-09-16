package com.quixindo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TaskDTO(@NotBlank @NotNull String description, @NotNull boolean status, UUID user_id) {
}
