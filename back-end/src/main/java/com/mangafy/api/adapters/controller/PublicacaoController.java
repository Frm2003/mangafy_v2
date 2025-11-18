package com.mangafy.api.adapters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mangafy.api.application.interfaces.IPublicacaoService;
import com.mangafy.api.domain.entity.Publicacao;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {
	
	@Autowired
	private IPublicacaoService publicacaoService;
	
	@GetMapping
	public ResponseEntity<List<Publicacao>> searchPublicacoes(
	        @RequestParam(required = false) String titulo,
	        @RequestParam(required = false) List<String> generos
	) {		
	    List<Publicacao> resultado = publicacaoService.findByTituloAndGeneroIds(titulo, generos);
	    return ResponseEntity.ok(resultado);
	}
}
