package com.mangafy.api.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {

}
