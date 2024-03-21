package com.app.todojpa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRecordDto(@NotNull @NotBlank String name,
                                @NotNull @NotBlank String email,
                                @NotNull @NotBlank String password) {
}
