package com.mangafy.api.application.interfaces;

import java.util.List;

import com.mangafy.api.adapters.dto.ComentarioDto;
import com.mangafy.api.domain.entity.Comentario;

public interface IComentarioService {
	List<Comentario> findAllByPublicacaoId(Long publicacaoId);
	Comentario findById(Long id);
	Comentario create(ComentarioDto dto);
	Comentario update(Long id, ComentarioDto dto);
	Comentario delete(Long id);
}
