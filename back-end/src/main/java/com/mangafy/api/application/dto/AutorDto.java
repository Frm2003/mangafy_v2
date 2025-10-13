package com.mangafy.api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorDto(
		@Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos") 
		String cpf,
		@Size(min = 14, max = 14, message = "CNPJ deve ter 14 dígitos")
		String cnpj,
		@NotBlank String email, 
		@NotBlank String nome, 
		String apelido,
		String senha
) {
	public AutorDto {
        if ((cpf == null || cpf.isBlank()) && (cnpj == null || cnpj.isBlank())) {
            throw new IllegalArgumentException("Pelo menos o CPF ou o CNPJ deve ser preenchido.");
        }
    }
}
