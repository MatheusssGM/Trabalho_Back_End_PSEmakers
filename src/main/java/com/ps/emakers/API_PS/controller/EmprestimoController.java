package com.ps.emakers.API_PS.controller;

import com.ps.emakers.API_PS.data.dto.request.EmprestimoRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.EmprestimoResponseDTO;
import com.ps.emakers.API_PS.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@Tag(name = "Empréstimo", description = "Endpoints para gerenciamento de empréstimos e devoluções")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Operation(summary = "Retorna todos os empréstimos",
            description = "Retorna uma lista de todos os empréstimos registrados no sistema.",
            tags = {"Empréstimo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimo());
    }

    @Operation(summary = "Retorna um empréstimo por ID",
            description = "Busca e retorna um empréstimo específico utilizando seu ID.",
            tags = {"Empréstimo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @GetMapping(value = "{idEmprestimo}", produces = "application/json")
    public ResponseEntity<EmprestimoResponseDTO> getEmprestimoById(@Valid @PathVariable Long idEmprestimo){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getEmprestimoById(idEmprestimo));
    }

    @Operation(summary = "Cria um novo empréstimo",
            description = "Cria um novo registro de empréstimo de livro no sistema.",
            tags = {"Empréstimo"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping(value = "/createEmp", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmprestimoResponseDTO> createEmprestimo(@Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.createEmprestimo(emprestimoRequestDTO));
    }

    @Operation(summary = "Registra a devolução de um empréstimo",
            description = "Altera o status de um empréstimo para indicar a devolução de um livro.",
            tags = {"Empréstimo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @PostMapping(value = "/createDev/{idEmprestimo}", produces = "application/json")
    public ResponseEntity<EmprestimoResponseDTO> createDevolucao(@Valid @PathVariable Long idEmprestimo){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.createDevolucao(idEmprestimo));
    }

    @Operation(summary = "Atualiza um empréstimo",
            description = "Atualiza os dados de um empréstimo existente no sistema.",
            tags = {"Empréstimo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EmprestimoResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @PutMapping(value = "/update/{idEmprestimo}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EmprestimoResponseDTO> updateEmprestimo(@Valid @PathVariable Long idEmprestimo,@Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.updateEmprestimo(idEmprestimo,emprestimoRequestDTO));
    }

    @Operation(summary = "Exclui um empréstimo",
            description = "Remove um empréstimo do sistema pelo seu ID.",
            tags = {"Empréstimo"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    @DeleteMapping(value = "/delete/{idEmprestimo}", produces = "text/plain")
    public ResponseEntity<String> deleteEmprestimo(@Valid @PathVariable Long idEmprestimo){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.deleteEmprestimo(idEmprestimo));
    }
}