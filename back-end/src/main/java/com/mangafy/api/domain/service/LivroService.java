package com.mangafy.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangafy.api.application.dto.PublicacaoDto;
import com.mangafy.api.application.service.IAutorService;
import com.mangafy.api.application.service.ILivroService;
import com.mangafy.api.domain.entity.Autor;
import com.mangafy.api.domain.entity.Genero;
import com.mangafy.api.domain.entity.Livro;
import com.mangafy.api.domain.repository.GeneroRepository;
import com.mangafy.api.domain.repository.LivroRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService implements ILivroService {
	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private IAutorService autorService;

	@Override
	public List<Livro> findAll() {
		return this.livroRepository.findAll();
	}

	@Override
	public Livro findById(Long id) {
		return this.livroRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Livro com ID " + id + " não encontrado."));
	}

	@Override
	public Livro create(PublicacaoDto dto) {
		Autor autor = this.autorService.findById(dto.autorId());

		List<Genero> generos = this.generoRepository.findAllById(dto.generosIds());

		Livro livroModel = new Livro();

		livroModel.setAutor(autor);
		livroModel.setGeneros(generos);
		livroModel.setTitulo(dto.titulo());
		livroModel.setSinopse(dto.sinopse());
		livroModel.setDataDeLancamento(dto.dataDeLancamento());
		livroModel.setISBN10(dto.ISBN10());
		livroModel.setISBN13(dto.ISBN13());
		livroModel.setCapaUrl(dto.capaUrl());
		livroModel.setNumPaginas(dto.numPaginas());
		livroModel.setStorageUrl(dto.storageUrl());

		return this.livroRepository.save(livroModel);
	}

	@Override
	public Livro update(Long id, PublicacaoDto dto) {
		Livro livroModel = this.findById(id);
		
		List<Genero> generos = this.generoRepository.findAllById(dto.generosIds());

		livroModel.setGeneros(generos);
		livroModel.setTitulo(dto.titulo());
		livroModel.setSinopse(dto.sinopse());
		livroModel.setCapaUrl(dto.capaUrl());
		livroModel.setNumPaginas(dto.numPaginas());
		
		return this.livroRepository.save(livroModel);
	}

	@Override
	public Livro delete(Long id) {
		return null;
	}

}
