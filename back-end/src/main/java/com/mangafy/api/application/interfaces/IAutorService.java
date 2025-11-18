package com.mangafy.api.application.interfaces;

import java.util.List;
import java.util.UUID;

import com.mangafy.api.adapters.dto.AutorDto;
import com.mangafy.api.domain.entity.Autor;

public interface IAutorService {
	List<Autor> findAll();
	Autor findById(UUID id);
	Autor create(AutorDto dto);
	Autor update(UUID id, AutorDto dto);
	Autor delete(UUID id);
}
