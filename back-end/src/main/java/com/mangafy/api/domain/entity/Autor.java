package com.mangafy.api.domain.entity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_AUTOR"));
	}

	@Override
	// Permite login por email, CPF ou CNPJ
	public String getUsername() {
		return super.getEmail();
	}
}
