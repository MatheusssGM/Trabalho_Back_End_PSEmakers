package com.ps.emakers.API_PS.data.entity;

import com.ps.emakers.API_PS.data.dto.request.LivroRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table (name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column (name = "nome", nullable = false, length = 100)
    private String name;

    @Column (name = "autor", nullable = false, length = 100)
    private String autor;

    @Column (name = "data_lancamento", nullable = false)
    @Past
    private LocalDate dataLancamento;

    @Column (name = "quantidade")
    private int quantidade;

    @Builder
    public Livro(LivroRequestDTO livroRequestDTO) {
        this.name = livroRequestDTO.name();
        this.autor = livroRequestDTO.autor();
        this.dataLancamento = livroRequestDTO.dataLancamento();
    }
}