package com.mangafy.api.application.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangafy.api.adapters.repository.PublicacaoRepository;
import com.mangafy.api.application.interfaces.IPublicacaoService;
import com.mangafy.api.domain.entity.Publicacao;

@Service
public class PublicacaoService implements IPublicacaoService {
	@Autowired
	private PublicacaoRepository publicacaoRepository;

	@Override
	public List<Publicacao> findByTituloAndGeneroIds(String titulo, List<String> generos) {
	    titulo = (titulo != null && !titulo.isBlank()) ? titulo : "";
	    
	    if (generos != null && !generos.isEmpty()) {
	        generos = generos.stream()
	                         .filter(g -> g != null && !g.isBlank())
	                         .map(String::trim)
	                         .collect(Collectors.toList());
	    } else {
	    	generos = null;
	    }

	    return publicacaoRepository.findByTituloAndGeneroIds(titulo, generos);
	}
}
