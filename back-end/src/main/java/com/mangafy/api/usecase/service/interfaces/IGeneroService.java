package com.mangafy.api.usecase.service.interfaces;

import java.util.List;

import com.mangafy.api.domain.entity.Genero;

public interface IGeneroService {
	List<Genero> findAll();
}
