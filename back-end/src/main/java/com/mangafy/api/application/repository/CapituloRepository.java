package com.mangafy.api.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Capitulo;

@Repository
public interface CapituloRepository extends JpaRepository<Capitulo, Integer> {
	@Query(value = "select * from capitulos c where c.manga_id = :mangaId", nativeQuery = true)
	List<Capitulo> findAllByMangaId(@Param("mangaId") Long mangaId);
}
