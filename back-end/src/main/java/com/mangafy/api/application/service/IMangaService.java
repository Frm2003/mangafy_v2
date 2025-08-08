package com.mangafy.api.application.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mangafy.api.application.dto.PublicacaoDto;
import com.mangafy.api.domain.entity.Manga;

public interface IMangaService {
	List<Manga> findAll();
	Manga findById(Long id);
	Manga create(PublicacaoDto dto, MultipartFile imagem, MultipartFile pdf) throws Exception;
	Manga update(Long id, PublicacaoDto dto, MultipartFile imagem, MultipartFile pdf) throws Exception;
	Manga delete(Long id);
}
