package com.ps.emakers.API_PS.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record EmprestimoRequestDTO(

        Long pessoa,

        Long livro,

        LocalDate dataEmprestimo,

        @Pattern(regexp = "^(Devolvido|Pendente)$")
        String situacao
) {
}
