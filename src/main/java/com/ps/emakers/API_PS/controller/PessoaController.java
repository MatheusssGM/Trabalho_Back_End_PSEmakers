package com.ps.emakers.API_PS.controller;

import com.ps.emakers.API_PS.data.dto.request.ChangePasswordRequestDTO;
import com.ps.emakers.API_PS.data.dto.request.PessoaRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.PessoaResponseDTO;
import com.ps.emakers.API_PS.data.entity.Pessoa;
import com.ps.emakers.API_PS.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<PessoaResponseDTO>> getAllPessoas(){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getAllPessoa());
    }

    @GetMapping(value = "{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> getPessoaById(@Valid @PathVariable Long idPessoa){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.getPessoaById(idPessoa));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<PessoaResponseDTO> createPessoa(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.createPessoa(pessoaRequestDTO));
    }

    @PutMapping(value = "/update/{idPessoa}")
    public ResponseEntity<PessoaResponseDTO> updatePessoa(@Valid @PathVariable Long idPessoa,@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updatePessoa(idPessoa,pessoaRequestDTO));
    }

    @PutMapping(value = "/changePassword")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequestDTO dto,Authentication authentication, Long idPessoa) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String username = jwt.getSubject();
        Pessoa pessoaLogada = pessoaService.findByEmail(username, idPessoa);
        pessoaService.changePassword(pessoaLogada.getIdPessoa(),dto.currentPassword(),dto.newPassword());

        return ResponseEntity.ok("Senha alterada com sucesso.");
    }

    @DeleteMapping(value = "/delete/{idPessoa}")
    public ResponseEntity<String> deletePessoa(@Valid @PathVariable Long idPessoa){
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.deletePessoa(idPessoa));
    }
}