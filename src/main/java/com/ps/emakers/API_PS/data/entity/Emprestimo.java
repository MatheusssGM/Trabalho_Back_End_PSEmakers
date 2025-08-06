package com.ps.emakers.API_PS.data.entity;

import com.ps.emakers.API_PS.data.dto.request.EmprestimoRequestDTO;
import jakarta.persistence.*;
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
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @ManyToOne()
    @JoinColumn(name = "idLivro")
    private Livro livro;

    @Column (name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Builder
    public Emprestimo(EmprestimoRequestDTO emprestimoRequestDTO) {
        this.pessoa = emprestimoRequestDTO.pessoa();
        this.livro = emprestimoRequestDTO.livro();
        this.dataEmprestimo = emprestimoRequestDTO.dataEmprestimo();
    }
}