package com.ps.emakers.API_PS.service;

import com.ps.emakers.API_PS.data.dto.request.PessoaRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.PessoaResponseDTO;
import com.ps.emakers.API_PS.data.entity.Pessoa;
import com.ps.emakers.API_PS.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaResponseDTO> getAllPessoa(){
        List<Pessoa> Pessoas = pessoaRepository.findAll();

        return Pessoas.stream().map(PessoaResponseDTO::new).collect(Collectors.toList());
    }

    public PessoaResponseDTO getPessoaById(Long idPessoa){
        Pessoa pessoa = getPessoaEntityById(idPessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa = new Pessoa(pessoaRequestDTO);
        pessoa.setName(pessoaRequestDTO.name());
        pessoa.setCpf(pessoaRequestDTO.cpf());
        pessoa.setCep(pessoaRequestDTO.cep());
        pessoa.setEmail(pessoaRequestDTO.email());
        pessoa.setSenha(pessoaRequestDTO.senha());

        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO updatePessoa(Long idPessoa, PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa= getPessoaEntityById(idPessoa);

        pessoa.setName(pessoaRequestDTO.name());
        pessoa.setCpf(pessoaRequestDTO.cpf());
        pessoa.setCep(pessoaRequestDTO.cep());
        pessoa.setEmail(pessoaRequestDTO.email());
        pessoa.setSenha(pessoaRequestDTO.senha());

        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public String deletePessoa(Long idPessoa){
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        pessoaRepository.delete(pessoa);

        return "Pessoa id:" + idPessoa + "deletado com sucesso!";
    }


    private Pessoa getPessoaEntityById(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new RuntimeException("Pessoa não encontrada"));
    }
}

