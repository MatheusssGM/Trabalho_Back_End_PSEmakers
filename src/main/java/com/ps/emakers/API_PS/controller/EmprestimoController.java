package com.ps.emakers.API_PS.controller;

import com.ps.emakers.API_PS.data.dto.request.EmprestimoRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.EmprestimoResponseDTO;
import com.ps.emakers.API_PS.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<EmprestimoResponseDTO>> getAllEmprestimos(){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getAllEmprestimo());
    }

    @GetMapping(value = "{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> getEmprestimoById(@Valid @PathVariable Long idEmprestimo){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.getEmprestimoById(idEmprestimo));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<EmprestimoResponseDTO> createEmprestimo(@Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.createEmprestimo(emprestimoRequestDTO));
    }

    @PutMapping(value = "/update/{idEmprestimo}")
    public ResponseEntity<EmprestimoResponseDTO> updateEmprestimo(@Valid @PathVariable Long idEmprestimo,@Valid @RequestBody EmprestimoRequestDTO emprestimoRequestDTO){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.updateEmprestimo(idEmprestimo,emprestimoRequestDTO));
    }

    @DeleteMapping(value = "/delete/{idEmprestimo}")
    public ResponseEntity<String> deleteEmprestimo(@Valid @PathVariable Long idEmprestimo){
        return ResponseEntity.status(HttpStatus.OK).body(emprestimoService.deleteEmprestimo(idEmprestimo));
    }
}