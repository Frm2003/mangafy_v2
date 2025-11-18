package com.mangafy.api.adapters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	@Query(value = "select * from comentarios c where c.publicacao_id = :publicacaoId", nativeQuery = true)
	List<Comentario> findAllByPublicacaoId(@Param("publicacaoId") Long publicacaoId);
}
