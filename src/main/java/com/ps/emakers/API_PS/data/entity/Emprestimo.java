package com.ps.emakers.API_PS.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table (name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEmprestimo;

    @ManyToOne()
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @ManyToOne()
    @JoinColumn(name = "idLivro")
    private Livro livro;
}
