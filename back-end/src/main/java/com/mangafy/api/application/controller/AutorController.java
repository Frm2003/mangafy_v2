package com.mangafy.api.application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangafy.api.application.dto.AutorDto;
import com.mangafy.api.domain.entity.Autor;
import com.mangafy.api.usecase.service.interfaces.IAutorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {
	@Autowired
	private IAutorService autorService;
	
	@GetMapping
	public ResponseEntity<List<Autor>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.autorService.findAll());
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Autor> findById(@PathVariable UUID id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.autorService.findById(id));
		} catch (EntityNotFoundException e) {			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
	}
	
	@PostMapping
	public ResponseEntity<Autor> create(@RequestBody @Valid AutorDto autorDto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.autorService.create(autorDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Autor> update(@PathVariable UUID id, @RequestBody @Valid AutorDto autorDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.autorService.update(id, autorDto));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Autor> delete(@PathVariable UUID id) {
		try {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.autorService.delete(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
