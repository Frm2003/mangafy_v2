package com.mangafy.api.application.interfaces;

import java.util.List;

import com.mangafy.api.domain.entity.Genero;

public interface IGeneroService {
	List<Genero> findAll();
}
