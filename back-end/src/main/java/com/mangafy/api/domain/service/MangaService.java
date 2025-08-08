package com.mangafy.api.domain.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mangafy.api.application.dto.PublicacaoDto;
import com.mangafy.api.application.service.IAutorService;
import com.mangafy.api.application.service.IMangaService;
import com.mangafy.api.application.service.IStorageService;
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

	@Autowired
	private IStorageService storageService;
	
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
	public Manga create(PublicacaoDto dto, MultipartFile imagem, MultipartFile pdf) throws IOException, Exception {
		Autor autor = this.autorService.findById(dto.autorId());

		List<Genero> generos = this.generoRepository.findAllById(dto.generosIds());
		
		Manga mangaModel = new Manga();
		
		String capaUrl = "/" + dto.autorId() + "/" + dto.ISBN10() + "/cover.jpg";
		
		mangaModel.setAutor(autor);
		mangaModel.setGeneros(generos);
		mangaModel.setTitulo(dto.titulo());
		mangaModel.setSinopse(dto.sinopse());
		mangaModel.setDataDeLancamento(dto.dataDeLancamento());
		mangaModel.setISBN10(dto.ISBN10());
		mangaModel.setISBN13(dto.ISBN13());
		mangaModel.setCapaUrl(capaUrl);
		mangaModel.setQtdCapitulos(dto.qtdCapitulos());
		
		storageService.upload(capaUrl, imagem.getInputStream(), imagem.getContentType(), imagem.getSize());
		
		return this.mangaRepository.save(mangaModel);
	}

	@Override
	public Manga update(Long id, PublicacaoDto dto, MultipartFile imagem, MultipartFile pdf) throws IOException, Exception {
		Manga mangaModel = this.findById(id);
		
		List<Genero> generos = this.generoRepository.findAllById(dto.generosIds());
		
		storageService.upload(mangaModel.getCapaUrl(), imagem.getInputStream(), imagem.getContentType(), imagem.getSize());
		
		mangaModel.setGeneros(generos);
		mangaModel.setTitulo(dto.titulo());
		mangaModel.setSinopse(dto.sinopse());
		mangaModel.setDataDeLancamento(dto.dataDeLancamento());
		
		return this.mangaRepository.save(mangaModel);
	}

	@Override
	public Manga delete(Long id) {
		return null;
	}
	
	
}
