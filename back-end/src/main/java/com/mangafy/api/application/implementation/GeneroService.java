package com.mangafy.api.application.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangafy.api.adapters.repository.GeneroRepository;
import com.mangafy.api.application.interfaces.IGeneroService;
import com.mangafy.api.domain.entity.Genero;

@Service
public class GeneroService implements IGeneroService {
	@Autowired
	private GeneroRepository generoRepository;
	
	@Override
	public List<Genero> findAll() {
		return this.generoRepository.findAll();
	}

}
