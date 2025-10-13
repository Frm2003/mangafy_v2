package com.mangafy.api.application.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Leitor;

@Repository
public interface LeitorRepository extends JpaRepository<Leitor, UUID> {
	Optional<Leitor> findByEmail(String email);
}
