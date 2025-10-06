package com.mangafy.api.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
	@Query(value = "select * from avaliacoes a where a.publicacao_id = :publicacaoId", nativeQuery = true)
	List<Avaliacao> findAllByPublicacaoId(@Param("publicacaoId") Long PublicacaoId);
}
