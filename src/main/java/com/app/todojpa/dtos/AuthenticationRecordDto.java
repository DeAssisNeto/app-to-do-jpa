package com.app.todojpa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRecordDto(@NotNull @NotBlank String login, @NotNull @NotBlank String password) {
}
