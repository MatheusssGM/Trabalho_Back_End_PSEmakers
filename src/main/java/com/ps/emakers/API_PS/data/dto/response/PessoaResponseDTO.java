package com.ps.emakers.API_PS.data.dto.response;

import com.ps.emakers.API_PS.data.entity.Pessoa;

public record PessoaResponseDTO(

        Long id,

        String name,

        String cpf,

        String cep,

        String email,

        String logradouro,

        String bairro,

        String localidade,

        String uf


) {
    public PessoaResponseDTO(Pessoa pessoa) {
        this(pessoa.getIdPessoa(), pessoa.getName(), pessoa.getCpf(), pessoa.getCep(), pessoa.getEmail(),pessoa.getLogradouro(), pessoa.getBairro(), pessoa.getLocalidade(),  pessoa.getUf());
    }
}