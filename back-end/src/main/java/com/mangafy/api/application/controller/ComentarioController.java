package com.mangafy.api.application.controller;

import java.util.List;

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

import com.mangafy.api.application.dto.ComentarioDto;
import com.mangafy.api.domain.entity.Comentario;
import com.mangafy.api.usecase.service.interfaces.IComentarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
	@Autowired
	private IComentarioService comentarioService;
	
	@GetMapping("/{publicacaoId}")
	public ResponseEntity<List<Comentario>> findAllByPublicacaoId(@PathVariable Long publicacaoId) {
		return  ResponseEntity.status(HttpStatus.OK).body(this.comentarioService.findAllByPublicacaoId(publicacaoId));
	}
	
	@PostMapping
	public ResponseEntity<Comentario> create(@RequestBody @Valid ComentarioDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.comentarioService.create(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Comentario> update(@PathVariable Long id, @RequestBody @Valid ComentarioDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.comentarioService.update(id, dto));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Comentario> delete(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.comentarioService.delete(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
