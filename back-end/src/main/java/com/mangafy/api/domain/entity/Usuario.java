package com.mangafy.api.domain.entity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING, length = 10)
@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Table(name = "usuarios")
@Setter
public class Usuario implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(length = 60)
	private String email;
	
	@Column(length = 100)
	private String nome;
	
	@Column(length = 60)
	private String apelido;

    @Column(length = 60)
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this instanceof Autor) {
            return List.of(new SimpleGrantedAuthority("ROLE_AUTOR"));
        }

        if (this instanceof Leitor leitor) {
            if (Boolean.TRUE.equals(leitor.getAssinante())) {
                return List.of(new SimpleGrantedAuthority("ROLE_LEITOR_ASSINANTE"));
            } else {
                return List.of(new SimpleGrantedAuthority("ROLE_LEITOR_NAO_ASSINANTE"));
            }
        }

        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
