package com.mangafy.api.domain.service;

import java.util.List;

import com.mangafy.api.domain.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangafy.api.application.dto.AvaliacaoDto;
import com.mangafy.api.application.service.IAvaliacaoService;
import com.mangafy.api.application.service.ILeitorService;
import com.mangafy.api.domain.entity.Avaliacao;
import com.mangafy.api.domain.entity.Leitor;
import com.mangafy.api.domain.entity.Publicacao;
import com.mangafy.api.domain.repository.AvaliacaoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AvaliacaoService implements IAvaliacaoService {
	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private PublicacaoRepository publicacaoRepository;
	
	@Autowired
	private ILeitorService leitorService;
		
	@Override
	public List<Avaliacao> findAllByPublicacaoId(Long id) {
		return this.avaliacaoRepository.findAllByPublicacaoId(id);
	}

	@Override
	public Avaliacao findById(Long id) {
		return this.avaliacaoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Avaliação com ID " + id + " não encontrado."));
	}
	
	@Override
	public Avaliacao create(AvaliacaoDto dto) {
		Leitor leitor = this.leitorService.findById(dto.leitorId());
		
		Publicacao publicacao = this.publicacaoRepository.findById(dto.publicacaoId())
				.orElseThrow(() -> new EntityNotFoundException("Publicação com ID " + dto.publicacaoId() + " não encontrado."));
			
		Avaliacao avaliacaoModel = new Avaliacao();
		
		avaliacaoModel.setLeitor(leitor);
		avaliacaoModel.setPublicacao(publicacao);
		avaliacaoModel.setDescricao(dto.descricao());
		avaliacaoModel.setNota(dto.nota());
		
		
		return this.avaliacaoRepository.save(avaliacaoModel);
	}

	@Override
	public Avaliacao update(Long id, AvaliacaoDto dto) {
		Avaliacao avaliacaoModel = this.findById(id);
		
		avaliacaoModel.setDescricao(dto.descricao());
		avaliacaoModel.setNota(dto.nota());
		
		return this.avaliacaoRepository.save(avaliacaoModel);
	}

	@Override
	public Avaliacao delete(Long id) {
		return null;
	}
}
