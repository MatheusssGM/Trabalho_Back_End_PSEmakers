package com.ps.emakers.API_PS.data.entity;

import com.ps.emakers.API_PS.data.dto.request.EmprestimoRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table (name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long idEmprestimo;

    @ManyToOne()
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne()
    @JoinColumn(name = "idLivro", nullable = false)
    private Livro livro;

    @Column (name = "data_emprestimo", nullable = false)
    @FutureOrPresent
    private LocalDate dataEmprestimo;

    @Column (name = "situacao", nullable = false)
    private String situacao;

    @Builder
    public Emprestimo(EmprestimoRequestDTO emprestimoRequestDTO) {
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setIdPessoa(emprestimoRequestDTO.pessoa());
        this.pessoa = pessoa1;
        Livro livro1 = new Livro();
        livro1.setIdLivro(emprestimoRequestDTO.livro());
        this.livro = livro1;
    }
}