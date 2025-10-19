package com.mangafy.api.domain.entity;

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
}
