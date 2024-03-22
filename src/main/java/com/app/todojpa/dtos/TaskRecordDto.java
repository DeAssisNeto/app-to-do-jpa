package com.app.todojpa.dtos;

import com.app.todojpa.roles.TaskRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRecordDto(@NotNull @NotBlank String description,
                            @NotNull TaskRole priority) {
}
