package com.ps.emakers.API_PS.service;

import com.ps.emakers.API_PS.data.dto.request.PessoaRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.PessoaResponseDTO;
import com.ps.emakers.API_PS.data.dto.viacep.EnderecoViaCep;
import com.ps.emakers.API_PS.data.entity.Pessoa;
import com.ps.emakers.API_PS.exceptions.general.GeneralException;
import com.ps.emakers.API_PS.exceptions.general.EntityNotFoundException;
import com.ps.emakers.API_PS.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    private final BCryptPasswordEncoder passwordEncoder;

    public PessoaService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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

        if (endereco.logradouro() != null) {
            pessoa.setLogradouro(endereco.logradouro());
            pessoa.setBairro(endereco.bairro());
            pessoa.setLocalidade(endereco.localidade());
            pessoa.setUf(endereco.uf());
        } else {
            throw new GeneralException("CEP inválido ou não encontrado.");
        }
        String encryptedSenha = new BCryptPasswordEncoder().encode(pessoaRequestDTO.senha());
        pessoa.setSenha(encryptedSenha);
        pessoa.setRole(pessoaRequestDTO.role());
        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }

    public PessoaResponseDTO updatePessoa(Long idPessoa, PessoaRequestDTO pessoaRequestDTO){
        Pessoa pessoa= getPessoaEntityById(idPessoa);

        if (!pessoa.getCep().equals(pessoaRequestDTO.cep())) {
            EnderecoViaCep endereco = enderecoService.buscarEndereco(pessoaRequestDTO.cep());

            if (endereco.logradouro() != null) {
                pessoa.setCep(pessoaRequestDTO.cep());
                pessoa.setLogradouro(endereco.logradouro());
                pessoa.setBairro(endereco.bairro());
                pessoa.setLocalidade(endereco.localidade());
                pessoa.setUf(endereco.uf());
            } else {
                throw new GeneralException("Novo CEP inválido ou não encontrado.");
            }
        }

        pessoa.setName(pessoaRequestDTO.name());
        pessoa.setCpf(pessoaRequestDTO.cpf());
        pessoa.setEmail(pessoaRequestDTO.email());
        pessoa.setSenha(pessoaRequestDTO.senha());

        pessoaRepository.save(pessoa);

        return new PessoaResponseDTO(pessoa);
    }


    @PutMapping(value = "/changePassword")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public void changePassword(Long idPessoa, String currentPassword, String newPassword) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new GeneralException("Pessoa não encontrada."));

        if (!passwordEncoder.matches(currentPassword, pessoa.getSenha())) {
            throw new GeneralException("Senha atual incorreta.");
        }

        String encryptedNewPassword = passwordEncoder.encode(newPassword);

        pessoa.setSenha(encryptedNewPassword);
        pessoaRepository.save(pessoa);
    }

    public String deletePessoa(Long idPessoa){
        Pessoa pessoa = getPessoaEntityById(idPessoa);
        pessoaRepository.delete(pessoa);

        return "Pessoa id: " + idPessoa + " deletado com sucesso!";
    }

    private Pessoa getPessoaEntityById(Long idPessoa){
        return pessoaRepository.findById(idPessoa).orElseThrow(()-> new EntityNotFoundException(idPessoa));
    }
    public Pessoa findByEmail(String email, Long idPessoa) {
        return pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(idPessoa));
    }
}