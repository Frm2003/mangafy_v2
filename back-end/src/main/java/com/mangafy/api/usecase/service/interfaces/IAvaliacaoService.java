package com.mangafy.api.usecase.service.interfaces;

import java.util.List;

import com.mangafy.api.application.dto.AvaliacaoDto;
import com.mangafy.api.domain.entity.Avaliacao;

public interface IAvaliacaoService {
	List<Avaliacao> findAllByPublicacaoId(Long id);
	Avaliacao findById(Long id);
	Avaliacao create(AvaliacaoDto dto);
	Avaliacao update(Long id, AvaliacaoDto dto);
	Avaliacao delete(Long id);
}
