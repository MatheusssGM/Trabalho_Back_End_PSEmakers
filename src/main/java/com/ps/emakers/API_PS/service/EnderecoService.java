package com.ps.emakers.API_PS.service;

import com.ps.emakers.API_PS.client.ViaCepClient;
import com.ps.emakers.API_PS.data.dto.viacep.EnderecoViaCep;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private ViaCepClient viaCepClient;

    public EnderecoViaCep buscarEndereco(String cep) {
        try {
            return viaCepClient.buscarEnderecoPorCep(cep);
        } catch (FeignException.NotFound e) {
            throw new IllegalArgumentException("CEP não encontrado.");
        }
    }
}