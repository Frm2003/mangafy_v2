package com.mangafy.api.application.interfaces;

import java.util.List;

import com.mangafy.api.domain.entity.Publicacao;

public interface IPublicacaoService {
	List<Publicacao> findByTituloAndGeneroIds(String titulo, List<String> generos);
}
