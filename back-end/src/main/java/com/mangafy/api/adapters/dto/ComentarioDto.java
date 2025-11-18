package com.mangafy.api.adapters.dto;

import java.util.UUID;

public record ComentarioDto(
		String descricao,
		Long comentarioPai,
		UUID leitorId,
		Long publicacaoId
) {

}
