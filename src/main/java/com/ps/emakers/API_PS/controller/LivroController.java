package com.ps.emakers.API_PS.controller;

import com.ps.emakers.API_PS.data.dto.request.LivroRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.LivroResponseDTO;
import com.ps.emakers.API_PS.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<LivroResponseDTO>> getAllLivros(){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getAllLivro());
    }

    @GetMapping(value = "{idLivro}")
    public ResponseEntity<LivroResponseDTO> getLivroById(@Valid @PathVariable Long idLivro){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.getLivroById(idLivro));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<LivroResponseDTO> createLivro(@Valid @RequestBody LivroRequestDTO livroRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.createLivro(livroRequestDTO));
    }

    @PutMapping(value = "/update/{idLivro}")
    public ResponseEntity<LivroResponseDTO> updateLivro(@Valid @PathVariable Long idLivro,@Valid @RequestBody LivroRequestDTO livroRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.updateLivro(idLivro,livroRequestDTO));
    }

    @DeleteMapping(value = "/delete/{idLivro}")
    public ResponseEntity<String> deleteLivro(@Valid @PathVariable Long idLivro){
        return ResponseEntity.status(HttpStatus.OK).body(livroService.deleteLivro(idLivro));
    }
}
