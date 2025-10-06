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

import com.mangafy.api.application.dto.AvaliacaoDto;
import com.mangafy.api.domain.entity.Avaliacao;
import com.mangafy.api.usecase.service.interfaces.IAvaliacaoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/avaliacoes")
public class AvalicaoController {
	@Autowired
	private IAvaliacaoService avaliacaoService;
	
	@GetMapping("/{publicacaoId}")
	public ResponseEntity<List<Avaliacao>> findAllByPublicacaoId(@PathVariable Long publicacaoId) {
		return  ResponseEntity.status(HttpStatus.OK).body(this.avaliacaoService.findAllByPublicacaoId(publicacaoId));
	}
	
	@PostMapping
	public ResponseEntity<Avaliacao> create(@RequestBody @Valid AvaliacaoDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.avaliacaoService.create(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Avaliacao> update(@PathVariable Long id, @RequestBody @Valid AvaliacaoDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.avaliacaoService.update(id, dto));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Avaliacao> delete(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.avaliacaoService.delete(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
