package com.ps.emakers.API_PS.service;

import com.ps.emakers.API_PS.data.dto.request.PessoaRequestDTO;
import com.ps.emakers.API_PS.data.dto.response.PessoaResponseDTO;
import com.ps.emakers.API_PS.data.dto.viacep.EnderecoViaCep;
import com.ps.emakers.API_PS.data.entity.Pessoa;
import com.ps.emakers.API_PS.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtService jwtService;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    PessoaRepository pessoaRepository;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public PessoaResponseDTO registerAdmin(PessoaRequestDTO pessoaRequestDTO){
        EnderecoViaCep endereco = enderecoService.buscarEndereco(pessoaRequestDTO.cep());

        Pessoa pessoa = new Pessoa();
        pessoa.setName(pessoaRequestDTO.name());
        pessoa.setCpf(pessoaRequestDTO.cpf());
        pessoa.setCep(pessoaRequestDTO.cep());
        pessoa.setLogradouro(endereco.logradouro());
        pessoa.setBairro(endereco.bairro());
        pessoa.setLocalidade(endereco.localidade());
        pessoa.setUf(endereco.uf());
        pessoa.setEmail(pessoaRequestDTO.email());
        String encryptedSenha = new BCryptPasswordEncoder().encode(pessoaRequestDTO.senha());
        pessoa.setSenha(encryptedSenha);
        pessoa.setRole(pessoaRequestDTO.role());

        this.pessoaRepository.save(pessoa);
        return new PessoaResponseDTO(pessoa);
    }

}