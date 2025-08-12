package com.ps.emakers.API_PS.controller;

import com.ps.emakers.API_PS.data.dto.viacep.EnderecoViaCep;
import com.ps.emakers.API_PS.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<EnderecoViaCep> buscarPorCep(@PathVariable String cep) {
        EnderecoViaCep endereco = enderecoService.buscarEndereco(cep);

        if (endereco != null) {
            return ResponseEntity.ok(endereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}