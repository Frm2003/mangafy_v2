package com.mangafy.api.application.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangafy.api.adapters.dto.ComentarioDto;
import com.mangafy.api.adapters.repository.ComentarioRepository;
import com.mangafy.api.adapters.repository.PublicacaoRepository;
import com.mangafy.api.application.interfaces.IComentarioService;
import com.mangafy.api.application.interfaces.ILeitorService;
import com.mangafy.api.domain.entity.Comentario;
import com.mangafy.api.domain.entity.Leitor;
import com.mangafy.api.domain.entity.Publicacao;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ComentarioService implements IComentarioService {
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private PublicacaoRepository publicacaoRepository;
	
	@Autowired
	private ILeitorService leitorService;
	
	@Override
	public List<Comentario> findAllByPublicacaoId(Long publicacaoId) {
		return this.comentarioRepository.findAllByPublicacaoId(publicacaoId);
	}

	@Override
	public Comentario findById(Long id) {
		return this.comentarioRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Comentário com ID " + id + " não encontrado."));
	}

	@Override
	public Comentario create(ComentarioDto dto) {
		Leitor leitor = this.leitorService.findById(dto.leitorId());
		
		Publicacao publicacao = this.publicacaoRepository.findById(dto.publicacaoId())
				.orElseThrow(() -> new EntityNotFoundException("Publicação com ID " + dto.publicacaoId() + " não encontrado."));
		
		Optional<Comentario> optional = this.comentarioRepository.findById(dto.comentarioPai());
		
		Comentario comentarioModel = new Comentario();
		
		comentarioModel.setLeitor(leitor);
		comentarioModel.setPublicacao(publicacao);
		comentarioModel.setDescricao(dto.descricao());
		comentarioModel.setComentarioPai(optional.isEmpty() ? null : optional.get());
		
		return this.comentarioRepository.save(comentarioModel);
	}

	@Override
	public Comentario update(Long id, ComentarioDto dto) {
		return null;
	}

	@Override
	public Comentario delete(Long id) {
		return null;
	}

}
