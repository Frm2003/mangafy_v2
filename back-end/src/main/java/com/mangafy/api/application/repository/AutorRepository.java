package com.mangafy.api.application.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
