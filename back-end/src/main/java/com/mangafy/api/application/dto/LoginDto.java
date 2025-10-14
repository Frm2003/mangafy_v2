package com.mangafy.api.application.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank String email,
        @NotBlank String senha
) {
}
