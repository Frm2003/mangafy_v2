package com.mangafy.api.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mangafy.api.domain.entity.Capitulo;
import com.mangafy.api.usecase.service.interfaces.ICapituloService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/capitulos")
public class CapituloController {
	@Autowired
	private ICapituloService capituloService;

	@GetMapping("/{mangaId}")
	public ResponseEntity<List<Capitulo>> findAllByMangaId(@PathVariable Long mangaId) {
		return ResponseEntity.status(HttpStatus.OK).body(this.capituloService.findAllByMangaId(mangaId));
	}

	@PostMapping(path = "/{mangaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Capitulo> create(@PathVariable Long mangaId,
			@RequestPart("imagem") List<MultipartFile> imagens) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.capituloService.create(mangaId, imagens));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Capitulo> delete(@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.capituloService.delete(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
