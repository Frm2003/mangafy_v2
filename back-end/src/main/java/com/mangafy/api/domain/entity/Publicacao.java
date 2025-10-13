package com.mangafy.api.domain.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorColumn(name = "tipo_publicacao", discriminatorType = DiscriminatorType.STRING, length = 10)
@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Table(name = "publicacoes")
@Setter
public class Publicacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "autor_id", nullable = false, referencedColumnName = "id")
	@JsonIgnore
	private Autor autor;

	@ManyToMany
	@JoinTable(
		joinColumns = @JoinColumn(name = "publicacao_id"), 
		name = "publicacao_genero", 
		inverseJoinColumns = @JoinColumn(name = "genero_id")
	)
	private List<Genero> generos;

	@Column(length = 50, nullable = false)
	private String titulo;
	
	@Column(length = 650, nullable = false)
	private String sinopse;
	
	@Column(name = "data_lancamento", nullable = false)
	private LocalDateTime dataDeLancamento;
	
	@Column(length = 10, unique = true)
	private String ISBN10;

	@Column(length = 13, unique = true)
	private String ISBN13;
	
	@Column(name = "capa_url")
	private String capaUrl;
}
