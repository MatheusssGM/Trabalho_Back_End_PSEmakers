package com.ps.emakers.API_PS.data.dto.response;


import com.ps.emakers.API_PS.data.entity.Emprestimo;
import com.ps.emakers.API_PS.data.entity.Livro;

import java.time.LocalDate;

public record EmprestimoResponseDTO(

        Long id,

        PessoaResponseDTO pessoa,

        Livro livro,

        LocalDate dataEmprestimo,

        String situacao
) {
    public EmprestimoResponseDTO(Emprestimo emprestimo){
        this(emprestimo.getIdEmprestimo(),new PessoaResponseDTO(emprestimo.getPessoa()),emprestimo.getLivro(),emprestimo.getDataEmprestimo(),emprestimo.getSituacao());
    }
}
