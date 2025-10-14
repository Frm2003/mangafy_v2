package com.mangafy.api.application.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Autor;
import com.mangafy.api.domain.entity.Usuario;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
	Optional<Usuario> findByEmail(String email);
}
