package com.mangafy.api.domain.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorValue("AUTOR")
@Entity
@Getter
@NoArgsConstructor
@Setter
public class Autor extends Usuario {
	@Column(length = 14, unique = true)
	private String cpf;
	
	@Column(length = 14, unique = true)
	private String cnpj;

	public Autor(UUID id, String email, String nome, String apelido, String cpf, String cnpj) {
		this.setId(id);
        this.setEmail(email);
        this.setNome(nome);
        this.setApelido(apelido);
        this.cpf = cpf;
        this.cnpj = cnpj;
    }
}
