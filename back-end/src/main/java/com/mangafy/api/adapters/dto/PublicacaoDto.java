package com.mangafy.api.adapters.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.mangafy.api.domain.entity.Capitulo;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PublicacaoDto(
		@NotNull UUID autorId,
		@NotNull List<Integer> generosIds,
		@NotNull String titulo,
        String sinopse,
        LocalDateTime dataDeLancamento,
        @Nullable @Size(min = 10, max = 10) String ISBN10,
        @Nullable @Size(min = 13, max = 13) String ISBN13,
        Integer numPaginas,
        Integer qtdCapitulos,
        List<Capitulo> capitulos
) {

}
