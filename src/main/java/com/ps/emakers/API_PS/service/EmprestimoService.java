package com.ps.emakers.API_PS.service;

import com.ps.emakers.API_PS.data.dto.request.EmprestimoRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.EmprestimoResponseDTO;
import com.ps.emakers.API_PS.data.entity.Emprestimo;
import com.ps.emakers.API_PS.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public List<EmprestimoResponseDTO> getAllEmprestimo(){
        List<Emprestimo> Emprestimos = emprestimoRepository.findAll();

        return Emprestimos.stream().map(EmprestimoResponseDTO::new).collect(Collectors.toList());
    }

    public EmprestimoResponseDTO getEmprestimoById(Long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public EmprestimoResponseDTO createEmprestimo(EmprestimoRequestDTO emprestimoRequestDTO){
        Emprestimo emprestimo = new Emprestimo(emprestimoRequestDTO);
        emprestimo.setPessoa(emprestimoRequestDTO.pessoa());
        emprestimo.setLivro(emprestimoRequestDTO.livro());
        emprestimo.setDataEmprestimo(emprestimoRequestDTO.dataEmprestimo());

        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public EmprestimoResponseDTO updateEmprestimo(Long idEmprestimo, EmprestimoRequestDTO emprestimoRequestDTO){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);

        emprestimo.setPessoa(emprestimoRequestDTO.pessoa());
        emprestimo.setLivro(emprestimoRequestDTO.livro());
        emprestimo.setDataEmprestimo(emprestimoRequestDTO.dataEmprestimo());

        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public String deleteEmprestimo(Long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);
        emprestimoRepository.delete(emprestimo);

        return "Emprestimo id:" + idEmprestimo + "deletado com sucesso!";
    }

    private Emprestimo getEmprestimoEntityById(Long idEmprestimo){
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(()-> new RuntimeException("Emprestimo não encontrado"));
    }
}
