package com.mangafy.api.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mangafy.api.application.dto.PublicacaoDto;
import com.mangafy.api.domain.entity.Livro;
import com.mangafy.api.usecase.service.interfaces.ILivroService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/livros")
public class LivroController {
	@Autowired
	private ILivroService livroService;

	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	public ResponseEntity<List<Livro>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.livroService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.livroService.findById(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Livro> create(@RequestPart("dto") String dtoJson,
			@RequestPart("imagem") MultipartFile imagem, @RequestPart("pdf") MultipartFile pdf) {
		try {
			PublicacaoDto dto = this.objectMapper.readValue(dtoJson, PublicacaoDto.class);

			return ResponseEntity.status(HttpStatus.CREATED).body(this.livroService.create(dto, imagem, pdf));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Livro> update(@PathVariable Long id, @RequestPart("dto") String dtoJson,
			@RequestPart("imagem") MultipartFile imagem, @RequestPart("pdf") MultipartFile pdf) {
		try {
			PublicacaoDto dto = this.objectMapper.readValue(dtoJson, PublicacaoDto.class);

			return ResponseEntity.status(HttpStatus.OK).body(this.livroService.update(id, dto, imagem, pdf));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Livro> delete(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.livroService.delete(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
