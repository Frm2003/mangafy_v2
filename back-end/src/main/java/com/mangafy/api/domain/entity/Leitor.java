package com.mangafy.api.domain.entity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;
import lombok.Setter;

@DiscriminatorValue("LEITOR")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Leitor extends Usuario {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name = "leitor_id"), name = "ler_mais_tarde", inverseJoinColumns = @JoinColumn(name = "publicacao_id"))
	private List<Publicacao> lerMaisTarde;

	public Leitor(UUID id, String email, String nome, String apelido, String cpf, String cnpj,
			List<Publicacao> lerMaisTarde, List<Avaliacao> avaliacoes) {
		this.setId(id);
		this.setEmail(email);
		this.setNome(nome);
		this.setApelido(apelido);
		this.lerMaisTarde = lerMaisTarde;
	}
	
	@Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (super.isAssinante()) {
            return List.of(new SimpleGrantedAuthority("ROLE_ASSINANTE"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_LEITOR"));
        }
    }
}
