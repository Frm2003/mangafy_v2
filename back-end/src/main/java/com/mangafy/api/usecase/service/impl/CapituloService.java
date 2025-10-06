package com.mangafy.api.usecase.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mangafy.api.application.adapter.IStorageAdapter;
import com.mangafy.api.application.repository.CapituloRepository;
import com.mangafy.api.domain.entity.Capitulo;
import com.mangafy.api.domain.entity.Manga;
import com.mangafy.api.domain.entity.Pagina;
import com.mangafy.api.usecase.service.interfaces.ICapituloService;


@Service
public class CapituloService implements ICapituloService {
	@Autowired
	private CapituloRepository capituloRepository;

	@Autowired
	private MangaService mangaService;

	@Autowired
	private IStorageAdapter storageAdapter;

	@Override
	public List<Capitulo> findAllByMangaId(Long id) {
		return this.capituloRepository.findAllByMangaId(id);
	}

	@Override
	public Capitulo create(Long mangaId, List<MultipartFile> imagens) throws IOException, Exception {
		Manga manga = this.mangaService.updateQtyChapters(mangaId);
		
		String titulo = manga.getTitulo().trim();
		String capitulo = "chapter" + manga.getQtdCapitulos();
		
		String initialPath = "/" + manga.getAutor().getId() + "/" + titulo + "/content/" + capitulo;

		Capitulo capituloModel = new Capitulo();
		List<Pagina> paginas = new ArrayList<>();
		
		for (int i = 0; i < imagens.size(); i++) {
			MultipartFile imagem = imagens.get(i);
			String storageUrl = initialPath + "/" + i;
			storageAdapter.upload(storageUrl, imagem.getInputStream(), imagem.getContentType(), imagem.getSize());
			paginas.add(new Pagina(storageUrl));
		}
		
		capituloModel.setManga(manga);
		capituloModel.setPaginas(paginas);
		
		return this.capituloRepository.save(capituloModel);
	}

	@Override
	public Capitulo delete(Integer id) {
		return null;
	}

}
