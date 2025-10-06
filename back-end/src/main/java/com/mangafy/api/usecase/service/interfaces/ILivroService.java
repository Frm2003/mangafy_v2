package com.mangafy.api.usecase.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mangafy.api.application.dto.PublicacaoDto;
import com.mangafy.api.domain.entity.Livro;

public interface ILivroService {
	List<Livro> findAll();
	Livro findById(Long id);
	Livro create(PublicacaoDto dto, MultipartFile imagem, MultipartFile pdf) throws Exception;
	Livro update(Long id, PublicacaoDto dto, MultipartFile imagem, MultipartFile pdf) throws Exception;
	Livro delete(Long id);
}
