package com.mangafy.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangafy.api.application.dto.PublicacaoDto;
import com.mangafy.api.application.service.IAutorService;
import com.mangafy.api.application.service.IMangaService;
import com.mangafy.api.domain.entity.Autor;
import com.mangafy.api.domain.entity.Genero;
import com.mangafy.api.domain.entity.Manga;
import com.mangafy.api.domain.repository.GeneroRepository;
import com.mangafy.api.domain.repository.MangaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MangaService implements IMangaService {
	@Autowired
	private MangaRepository mangaRepository;
	
	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private IAutorService autorService;

	@Override
	public List<Manga> findAll() {
		return this.mangaRepository.findAll();
	}

	@Override
	public Manga findById(Long id) {
		return this.mangaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Livro com ID " + id + " não encontrado."));
	}

	@Override
	public Manga create(PublicacaoDto dto) {
		Autor autor = this.autorService.findById(dto.autorId());

		List<Genero> generos = this.generoRepository.findAllById(dto.generosIds());
		
		Manga mangaModel = new Manga();
		
		mangaModel.setAutor(autor);
		mangaModel.setGeneros(generos);
		mangaModel.setTitulo(dto.titulo());
		mangaModel.setSinopse(dto.sinopse());
		mangaModel.setDataDeLancamento(dto.dataDeLancamento());
		mangaModel.setISBN10(dto.ISBN10());
		mangaModel.setISBN13(dto.ISBN13());
		mangaModel.setCapaUrl(dto.capaUrl());
		mangaModel.setCapitulos(dto.capitulos());
		mangaModel.setQtdCapitulos(dto.qtdCapitulos());
		
		return this.mangaRepository.save(mangaModel);
	}

	@Override
	public Manga update(Long id, PublicacaoDto dto) {
		Manga mangaModel = this.findById(id);
		
		List<Genero> generos = this.generoRepository.findAllById(dto.generosIds());
		
		mangaModel.setGeneros(generos);
		mangaModel.setTitulo(dto.titulo());
		mangaModel.setSinopse(dto.sinopse());
		mangaModel.setCapaUrl(dto.capaUrl());
		
		return this.mangaRepository.save(mangaModel);
	}

	@Override
	public Manga delete(Long id) {
		return null;
	}
	
	
}
