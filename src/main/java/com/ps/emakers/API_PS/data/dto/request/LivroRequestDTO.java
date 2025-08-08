package com.ps.emakers.API_PS.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record LivroRequestDTO(

        @NotBlank(message = "Nome obrigatório")
        String  name,

        @NotBlank(message = "Autor obrigatório")
        String autor,

        @NotNull(message = "Data de lançamento obrigatória")
        LocalDate dataLancamento
) {
}
