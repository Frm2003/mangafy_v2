package com.mangafy.api.adapters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Manga;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long> {

}
