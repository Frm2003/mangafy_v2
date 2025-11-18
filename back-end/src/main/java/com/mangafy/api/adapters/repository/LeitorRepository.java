package com.mangafy.api.adapters.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Leitor;
import com.mangafy.api.domain.entity.Usuario;

@Repository
public interface LeitorRepository extends JpaRepository<Leitor, UUID> {
	Optional<Usuario> findByEmail(String email);
}
