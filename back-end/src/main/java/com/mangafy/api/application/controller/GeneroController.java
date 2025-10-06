package com.mangafy.api.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangafy.api.domain.entity.Genero;
import com.mangafy.api.usecase.service.interfaces.IGeneroService;

@RestController
@RequestMapping("/generos")
public class GeneroController {
	@Autowired
	private IGeneroService generoService;
	
	@GetMapping
	public ResponseEntity<List<Genero>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.generoService.findAll());
	}
}
