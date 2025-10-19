package com.mangafy.api.domain.entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorValue("LEITOR")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Leitor extends Usuario {
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "leitor_id"), name = "ler_mais_tarde", inverseJoinColumns = @JoinColumn(name = "publicacao_id"))
	private List<Publicacao> lerMaisTarde;

    @Column
    private Boolean assinante;
}
