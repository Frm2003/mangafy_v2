package com.mangafy.api.usecase.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mangafy.api.domain.entity.Capitulo;

public interface ICapituloService {
	List<Capitulo> findAllByMangaId(Long id);
	Capitulo create(Long mangaId, List<MultipartFile> imagens) throws IOException, Exception;
	Capitulo delete(Integer id); 
}
