package com.ps.emakers.API_PS.controller;

import com.ps.emakers.API_PS.data.dto.request.PessoaRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.LoginResponseDTO;
import com.ps.emakers.API_PS.data.dto.response.PessoaResponseDTO;
import com.ps.emakers.API_PS.service.AuthenticationService;
import com.ps.emakers.API_PS.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private final AuthenticationService authenticantionService;

    public AuthenticationController(AuthenticationService authenticantionService) {
        this.authenticantionService = authenticantionService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(pessoaRequestDTO.email(), pessoaRequestDTO.senha());
        Authentication auth = this.authenticationManager.authenticate(authToken);
        var token = jwtService.generateToken(auth);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<PessoaResponseDTO> registerAdmin(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authenticantionService.registerAdmin(pessoaRequestDTO));
    }
}
