package com.ps.emakers.API_PS.data.dto.response;

import com.ps.emakers.API_PS.data.entity.Livro;

import java.time.LocalDate;

public record LivroResponseDTO(

        Long id,

        String name,

        String autor,

        LocalDate dataLancamento

) {
    public LivroResponseDTO(Livro livro){
        this(livro.getIdLivro(), livro.getName(),livro.getAutor(),livro.getDataLancamento());
    }
}
