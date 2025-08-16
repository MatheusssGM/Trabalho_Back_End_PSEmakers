package com.ps.emakers.API_PS.controller;

import com.ps.emakers.API_PS.data.dto.request.PessoaRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.LoginResponseDTO;
import com.ps.emakers.API_PS.data.dto.response.PessoaResponseDTO;
import com.ps.emakers.API_PS.exceptions.RestErrorMessage;
import com.ps.emakers.API_PS.service.AuthenticationService;
import com.ps.emakers.API_PS.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Authentication", description = "Endpoints relacionados à área de Authentication")
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

    @Operation(summary = "Autentica um usuário e retorna um token JWT",
            description = "Gera um token JWT com base nas credenciais do usuário para acesso aos endpoints protegidos.",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
            }
    )
    @PostMapping(value = "/login",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<?> login(@RequestBody PessoaRequestDTO pessoaRequestDTO) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(pessoaRequestDTO.email(), pessoaRequestDTO.senha());
            Authentication auth = this.authenticationManager.authenticate(authToken);
            var token = jwtService.generateToken(auth);
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (AuthenticationException e) {
            RestErrorMessage error = new RestErrorMessage(HttpStatus.UNAUTHORIZED, "Credenciais de login inválidas.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }

    @Operation(summary = "Registra um usuário no sistema",
            description = "Registra uma Pessoa com base nas credenciais do usuário.",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PessoaResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @PostMapping(value = "/register",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<PessoaResponseDTO> register(@Valid @RequestBody PessoaRequestDTO pessoaRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(authenticantionService.register(pessoaRequestDTO));
    }
}