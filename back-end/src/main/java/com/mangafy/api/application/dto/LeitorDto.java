package com.mangafy.api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LeitorDto(
		@NotBlank String email, 
		@NotBlank String nome, 
		String apelido,
        @NotBlank  @Size(min = 8, max = 32) String senha
) {

}
