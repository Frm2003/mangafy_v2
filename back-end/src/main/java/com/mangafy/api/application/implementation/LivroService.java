package com.mangafy.api.application.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mangafy.api.adapters.adapter.IStorageAdapter;
import com.mangafy.api.adapters.dto.PublicacaoDto;
import com.mangafy.api.adapters.repository.GeneroRepository;
import com.mangafy.api.adapters.repository.LivroRepository;
import com.mangafy.api.application.interfaces.IAutorService;
import com.mangafy.api.application.interfaces.ILivroService;
import com.mangafy.api.domain.entity.Autor;
import com.mangafy.api.domain.entity.Genero;
import com.mangafy.api.domain.entity.Livro;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LivroService implements ILivroService {	
	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private GeneroRepository generoRepository;

	@Autowired
	private IAutorService autorService;

	@Autowired
	private IStorageAdapter storageAdapter;

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
	public Livro create(PublicacaoDto dto, MultipartFile imagem, MultipartFile pdf) throws Exception {
		Autor autor = this.autorService.findById(dto.autorId());

		List<Genero> generos = this.generoRepository.findAllById(dto.generosIds());

		Livro livroModel = new Livro();

		String capaUrl = "/" + dto.autorId() + "/" + dto.ISBN10() + "/cover.jpg";
		String storageUrl = "/" + dto.autorId() + "/" + dto.ISBN10() + "/content.pdf";

		livroModel.setAutor(autor);
		livroModel.setGeneros(generos);
		livroModel.setTitulo(dto.titulo());
		livroModel.setSinopse(dto.sinopse());
		livroModel.setDataDeLancamento(dto.dataDeLancamento());
		livroModel.setISBN10(dto.ISBN10());
		livroModel.setISBN13(dto.ISBN13());
		livroModel.setCapaUrl(capaUrl);
		livroModel.setStorageUrl(storageUrl);
		livroModel.setNumPaginas(dto.numPaginas());
		
		livroModel.setPreemium(false);

		storageAdapter.upload(capaUrl, imagem.getInputStream(), imagem.getContentType(), imagem.getSize());
		storageAdapter.upload(storageUrl, pdf.getInputStream(), pdf.getContentType(), pdf.getSize());

		return this.livroRepository.save(livroModel);
	}

	@Override
	public Livro update(Long id, PublicacaoDto dto, MultipartFile imagem, MultipartFile pdf) throws Exception {
		Livro livroModel = this.findById(id);
		
		List<Genero> generos = this.generoRepository.findAllById(dto.generosIds());

		storageAdapter.upload(livroModel.getCapaUrl(), imagem.getInputStream(), imagem.getContentType(), imagem.getSize());
		storageAdapter.upload(livroModel.getStorageUrl(), pdf.getInputStream(), pdf.getContentType(), pdf.getSize());
		
		livroModel.setGeneros(generos);
		livroModel.setTitulo(dto.titulo());
		livroModel.setSinopse(dto.sinopse());
		livroModel.setNumPaginas(dto.numPaginas());
		livroModel.setDataDeLancamento(dto.dataDeLancamento());
		
		return this.livroRepository.save(livroModel);
	}

	@Override
	public Livro delete(Long id) {
		return null;
	}

}