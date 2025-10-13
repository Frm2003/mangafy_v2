package com.mangafy.api.application.dto;

import jakarta.validation.constraints.NotBlank;

public record LeitorDto(
		@NotBlank String email, 
		@NotBlank String nome, 
		String apelido,
		String senha
) {

}
