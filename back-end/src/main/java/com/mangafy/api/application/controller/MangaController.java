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
import com.mangafy.api.domain.entity.Manga;
import com.mangafy.api.usecase.service.interfaces.IMangaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/mangas")
public class MangaController {
	@Autowired
	private IMangaService mangaService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping
	public ResponseEntity<List<Manga>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.mangaService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Manga> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.mangaService.findById(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Manga> create(@RequestPart("dto") String dtoJson, @RequestPart("imagem") MultipartFile imagem,
			@RequestPart("pdf") MultipartFile pdf) {
		try {
			PublicacaoDto dto = objectMapper.readValue(dtoJson, PublicacaoDto.class);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(this.mangaService.create(dto, imagem, pdf));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Manga> update(@PathVariable Long id, @RequestPart("dto") String dtoJson,
			@RequestPart("imagem") MultipartFile imagem, @RequestPart("pdf") MultipartFile pdf) {
		try {
			PublicacaoDto dto = objectMapper.readValue(dtoJson, PublicacaoDto.class);
			
			return ResponseEntity.status(HttpStatus.OK).body(this.mangaService.update(id, dto, imagem, pdf));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Manga> delete(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.mangaService.delete(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
