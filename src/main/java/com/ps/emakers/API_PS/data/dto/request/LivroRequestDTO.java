package com.ps.emakers.API_PS.data.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record LivroRequestDTO(

        @NotBlank(message = "Nome obrigatório")
        String  name,

        @NotBlank(message = "Autor obrigatório")
        String autor,

        @NotBlank(message = "Data de lançamento obrigatório")
        LocalDate dataLancamento
) {
}
