package com.mangafy.api.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
