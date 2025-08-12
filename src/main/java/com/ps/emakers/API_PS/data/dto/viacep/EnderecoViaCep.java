package com.ps.emakers.API_PS.data.dto.viacep;

public record EnderecoViaCep(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf) {
}
