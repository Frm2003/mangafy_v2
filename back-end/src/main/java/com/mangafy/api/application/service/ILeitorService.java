package com.mangafy.api.application.service;

import java.util.List;
import java.util.UUID;

import com.mangafy.api.application.dto.LeitorDto;
import com.mangafy.api.domain.entity.Leitor;

public interface ILeitorService {
	List<Leitor> findAll();
	Leitor findById(UUID id);
	Leitor create(LeitorDto dto);
	Leitor update(UUID id, LeitorDto dto);
	Leitor delete(UUID id);
}
