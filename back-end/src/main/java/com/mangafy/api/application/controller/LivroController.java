package com.mangafy.api.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.mangafy.api.domain.entity.Leitor;
import com.mangafy.api.domain.entity.Livro;
import com.mangafy.api.domain.entity.Usuario;
import com.mangafy.api.usecase.service.interfaces.ILivroService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
	private ILivroService livroService;

	@Autowired
	private ObjectMapper objectMapper;

    LivroController(LeitorController leitorController) {
    }

	@GetMapping
	public ResponseEntity<List<Livro>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.livroService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
		try {
			// CONTATENA ROLES PARA VALIDAÇÃO
	        String roles = usuario.getAuthorities()
	                .stream()
	                .map(GrantedAuthority::getAuthority)
	                .collect(Collectors.joining(", "));

	        Livro livro = this.livroService.findById(id);

	        // Se for autor, tem acesso total
	        if (roles.contains("ROLE_AUTOR"))
	            return ResponseEntity.ok(livro);

	        // Se for leitor, verifica se é assinante e se o livro é premium
	        if (usuario instanceof Leitor leitor) {
	            Boolean isAssinante = Boolean.TRUE.equals(leitor.getAssinante());
	            Boolean isLivroPremium = Boolean.TRUE.equals(livro.getPreemium());

	            // Leitor assinante pode ver livros premium, ou qualquer livro não premium
	            if (!isLivroPremium || isAssinante) {
	                return ResponseEntity.ok(livro);
	            } else {
	                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	            }
	        }

	        // Caso não seja nem autor nem leitor (por segurança)
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);

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
