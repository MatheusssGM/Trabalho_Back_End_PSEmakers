package com.ps.emakers.API_PS.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table (name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome", nullable = false, length = 100)
    private String nome;

    @Column (name = "autor", nullable = false, length = 100)
    private String autor;

    @Column (name = "data_lancamento", nullable = false)
    private Date dataLancamento;
}
