package com.ps.emakers.API_PS.data.entity;

import com.ps.emakers.API_PS.data.dto.request.PessoaRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@Table (name = "pessoa")

public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPessoa;

    @Column (name = "name", nullable = false, length = 100)
    private String name;

    @Column (name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column (name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "logradouro", length = 100)
    private String logradouro;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "localidade", length = 100)
    private String localidade;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column (name = "email", nullable = false, length = 100)
    private String email;

    @Column (name = "senha", nullable = false, length = 100)
    private String senha;

    @Column (name = "Role")
    private UserRole role;

    public Pessoa(PessoaRequestDTO pessoaRequestDTO){
        this.name = pessoaRequestDTO.name();
        this.cpf = pessoaRequestDTO.cpf();
        this.cep = pessoaRequestDTO.cep();
        this.email = pessoaRequestDTO.email();
        this.senha = pessoaRequestDTO.senha();
        this.role = pessoaRequestDTO.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}