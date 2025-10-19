package com.mangafy.api.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mangafy.api.domain.entity.Publicacao;

@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
	@Query("SELECT DISTINCT p FROM Publicacao p " + "LEFT JOIN p.generos g "
			+ "WHERE (:titulo IS NULL OR p.titulo LIKE CONCAT('%', :titulo, '%')) "
			+ "AND (:generos IS NULL OR g.name IN :generos)")
	List<Publicacao> findByTituloAndGeneroIds(@Param("titulo") String titulo, @Param("generos") List<String> generoIds);
}
