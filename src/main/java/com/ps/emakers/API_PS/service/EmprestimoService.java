package com.ps.emakers.API_PS.service;

import com.ps.emakers.API_PS.data.dto.request.EmprestimoRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.EmprestimoResponseDTO;
import com.ps.emakers.API_PS.data.entity.Emprestimo;
import com.ps.emakers.API_PS.data.entity.Livro;
import com.ps.emakers.API_PS.data.entity.Pessoa;
import com.ps.emakers.API_PS.exceptions.general.EntityNotFoundException;
import com.ps.emakers.API_PS.repository.EmprestimoRepository;
import com.ps.emakers.API_PS.repository.LivroRepository;
import com.ps.emakers.API_PS.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LivroRepository livroRepository;

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
        Pessoa pessoa = getPessoaEntityById(emprestimoRequestDTO.pessoa());
        emprestimo.setPessoa(pessoa);
        Livro livro = getLivroEntityById(emprestimoRequestDTO.livro());
        emprestimo.setLivro(livro);
        LocalDate dataEmprestimo = LocalDate.now();
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setSituacao(emprestimoRequestDTO.situacao());

        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public EmprestimoResponseDTO updateEmprestimo(Long idEmprestimo, EmprestimoRequestDTO emprestimoRequestDTO){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);

        Pessoa pessoa = getPessoaEntityById(emprestimoRequestDTO.pessoa());
        emprestimo.setPessoa(pessoa);
        Livro livro = getLivroEntityById(emprestimoRequestDTO.livro());
        emprestimo.setLivro(livro);
        LocalDate dataEmprestimo = LocalDate.now();
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setSituacao(emprestimoRequestDTO.situacao());

        emprestimoRepository.save(emprestimo);

        return new EmprestimoResponseDTO(emprestimo);
    }

    public String deleteEmprestimo(Long idEmprestimo){
        Emprestimo emprestimo = getEmprestimoEntityById(idEmprestimo);
        emprestimoRepository.delete(emprestimo);

        return "Emprestimo id: " + idEmprestimo + " deletado com sucesso!";
    }

    private Emprestimo getEmprestimoEntityById(Long idEmprestimo){
        return emprestimoRepository.findById(idEmprestimo).orElseThrow(()-> new EntityNotFoundException(idEmprestimo));
    }

    private Pessoa getPessoaEntityById(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new EntityNotFoundException(idPessoa));
    }

    private Livro getLivroEntityById(Long idLivro){
        return livroRepository.findById(idLivro).orElseThrow(()-> new EntityNotFoundException(idLivro));
    }
}
