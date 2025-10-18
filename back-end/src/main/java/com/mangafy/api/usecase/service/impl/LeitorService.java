package com.mangafy.api.usecase.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mangafy.api.application.dto.LeitorDto;
import com.mangafy.api.application.repository.LeitorRepository;
import com.mangafy.api.domain.entity.Leitor;
import com.mangafy.api.usecase.service.interfaces.ILeitorService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LeitorService implements ILeitorService {
	@Autowired
	private LeitorRepository leitorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Leitor> findAll() {
		return this.leitorRepository.findAll();
	}

	@Override
	public Leitor findById(UUID id) {
		return this.leitorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Usuário com ID " + id + " não encontrado."));
	}

	@Override
	public Leitor create(LeitorDto dto) {
		Leitor leitorModel = new Leitor();
		
		leitorModel.setApelido(dto.apelido());
		leitorModel.setEmail(dto.email());
		leitorModel.setNome(dto.nome());
        leitorModel.setSenha(passwordEncoder.encode(dto.senha()));
		
        leitorModel.setAssinante(false);
        
		return this.leitorRepository.save(leitorModel);
	}

	@Override
	public Leitor update(UUID id, LeitorDto dto) {
		Leitor leitorModel = this.findById(id);
		
		leitorModel.setApelido(dto.apelido());
		leitorModel.setEmail(dto.email());
		leitorModel.setNome(dto.nome());
        leitorModel.setSenha(passwordEncoder.encode(dto.senha()));
		
		return this.leitorRepository.save(leitorModel);
	}

	@Override
	public Leitor delete(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
