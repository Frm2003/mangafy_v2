package com.mangafy.api.application.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mangafy.api.adapters.dto.AutorDto;
import com.mangafy.api.adapters.repository.AutorRepository;
import com.mangafy.api.application.interfaces.IAutorService;
import com.mangafy.api.domain.entity.Autor;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AutorService implements IAutorService {
	@Autowired
	private AutorRepository autorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public List<Autor> findAll() {
		return this.autorRepository.findAll();
	}

	@Override
	public Autor findById(UUID id) {
		return this.autorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Autor com ID " + id + " não encontrado."));
	}

	@Override
	public Autor create(AutorDto dto) {
		Autor autorModel = new Autor();

		autorModel.setApelido(dto.apelido());
		autorModel.setCnpj(dto.cnpj());
		autorModel.setCpf(dto.cpf());
		autorModel.setEmail(dto.email());
		autorModel.setNome(dto.nome());
        autorModel.setSenha(passwordEncoder.encode(dto.senha()));

		return this.autorRepository.save(autorModel);
	}

	@Override
	public Autor update(UUID id, AutorDto dto) {
		Autor autorModel = this.findById(id);

		autorModel.setApelido(dto.apelido());
		autorModel.setCnpj(autorModel.getCnpj() == null ? dto.cnpj() : autorModel.getCnpj());
		autorModel.setCpf(autorModel.getCpf() == null ? dto.cpf() : autorModel.getCpf());
		autorModel.setEmail(dto.email());
		autorModel.setNome(dto.nome());
        autorModel.setSenha(passwordEncoder.encode(dto.senha()));

		return this.autorRepository.save(autorModel);
	}

	@Override
	public Autor delete(UUID id) {
		return null;
	}

}
