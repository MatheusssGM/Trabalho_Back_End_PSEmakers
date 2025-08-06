package com.ps.emakers.API_PS.data.dto.request;

import com.ps.emakers.API_PS.data.entity.Livro;
import com.ps.emakers.API_PS.data.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record EmprestimoRequestDTO(

        Pessoa pessoa,

        Livro livro,

        @NotBlank (message = "Data do empréstimo obrigatória")
        LocalDate dataEmprestimo
) {
}
