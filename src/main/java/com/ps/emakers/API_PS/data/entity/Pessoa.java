package com.ps.emakers.API_PS.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table (name = "pessoa")

public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPessoa;

    @Column (name = "name", nullable = false, length = 100)
    private String name;

    @Column (name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column (name = "cep", nullable = false, length = 9)
    private String cep;

    @Column (name = "email", nullable = false, length = 100)
    private String email;

    @Column (name = "senha", nullable = false, length = 100)
    private String senha;

}
