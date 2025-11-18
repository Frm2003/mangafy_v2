package com.mangafy.api.application.interfaces;

import java.util.List;

import com.mangafy.api.adapters.dto.AvaliacaoDto;
import com.mangafy.api.domain.entity.Avaliacao;

public interface IAvaliacaoService {
	List<Avaliacao> findAllByPublicacaoId(Long id);
	Avaliacao findById(Long id);
	Avaliacao create(AvaliacaoDto dto);
	Avaliacao update(Long id, AvaliacaoDto dto);
	Avaliacao delete(Long id);
}
