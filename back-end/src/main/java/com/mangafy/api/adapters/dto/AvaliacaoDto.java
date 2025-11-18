package com.mangafy.api.adapters.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AvaliacaoDto(
		BigDecimal nota,
		String descricao,
		UUID leitorId,
		Long publicacaoId
) {

}
