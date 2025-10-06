package com.mangafy.api.usecase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangafy.api.application.repository.GeneroRepository;
import com.mangafy.api.domain.entity.Genero;
import com.mangafy.api.usecase.service.interfaces.IGeneroService;

@Service
public class GeneroService implements IGeneroService {
	@Autowired
	private GeneroRepository generoRepository;
	
	@Override
	public List<Genero> findAll() {
		return this.generoRepository.findAll();
	}

}
