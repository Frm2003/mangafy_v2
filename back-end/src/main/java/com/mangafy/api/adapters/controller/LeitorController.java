package com.mangafy.api.adapters.controller;

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

import com.mangafy.api.adapters.dto.LeitorDto;
import com.mangafy.api.application.interfaces.ILeitorService;
import com.mangafy.api.domain.entity.Leitor;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/leitores")
public class LeitorController {
	@Autowired
	private ILeitorService leitorService;
	
	@GetMapping
	public ResponseEntity<List<Leitor>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.leitorService.findAll());
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Leitor> findById(@PathVariable UUID id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.leitorService.findById(id));
		} catch (EntityNotFoundException e) {			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
	}
	
	@PostMapping
	public ResponseEntity<Leitor> create(@RequestBody @Valid LeitorDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.leitorService.create(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Leitor> update(@PathVariable UUID id, @RequestBody @Valid LeitorDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.leitorService.update(id, dto));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Leitor> delete(@PathVariable UUID id) {
		try {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.leitorService.delete(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
