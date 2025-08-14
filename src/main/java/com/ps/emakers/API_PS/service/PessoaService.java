package com.ps.emakers.API_PS.service;

import com.ps.emakers.API_PS.data.dto.request.PessoaRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.PessoaResponseDTO;
import com.ps.emakers.API_PS.data.dto.viacep.EnderecoViaCep;
import com.ps.emakers.API_PS.data.entity.Pessoa;
import com.ps.emakers.API_PS.exceptions.general.EntityNotFoundException;
import com.ps.emakers.API_PS.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    public List<PessoaResponseDTO> getAllPessoa(){
        List<Pessoa> Pessoas = pessoaRepository.findAll();
        return Pessoas.stream().map(PessoaResponseDTO::new).collect(Collectors.toList());
    }

    public PessoaResponseDTO getPessoaById(Long idPessoa){
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO createPessoa(PessoaRequestDTO pessoaRequestDTO){
        EnderecoViaCep endereco = enderecoService.buscarEndereco(pessoaRequestDTO.cep());

        Pessoa pessoa = new Pessoa(pessoaRequestDTO);

        if (endereco != null) {
            pessoa.setLogradouro(endereco.logradouro());
            pessoa.setBairro(endereco.bairro());
            pessoa.setLocalidade(endereco.localidade());
            pessoa.setUf(endereco.uf());
        } else {
            throw new IllegalArgumentException("CEP inválido ou não encontrado.");
        }
        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO updatePessoa(Long idPessoa, PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa= getPessoaEntityById(idPessoa);

        if (!pessoa.getCep().equals(pessoaRequestDTO.cep())) {
            EnderecoViaCep endereco = enderecoService.buscarEndereco(pessoaRequestDTO.cep());

            if (endereco != null) {
                pessoa.setCep(pessoaRequestDTO.cep()); // Atualiza o CEP
                pessoa.setLogradouro(endereco.logradouro());
                pessoa.setBairro(endereco.bairro());
                pessoa.setLocalidade(endereco.localidade());
                pessoa.setUf(endereco.uf());
            } else {
                throw new IllegalArgumentException("Novo CEP inválido ou não encontrado.");
            }
        }

        pessoa.setName(pessoaRequestDTO.name());
        pessoa.setCpf(pessoaRequestDTO.cpf());
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
        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new EntityNotFoundException(idPessoa));
    }
}