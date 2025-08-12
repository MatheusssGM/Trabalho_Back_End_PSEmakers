package com.ps.emakers.API_PS.repository;

import com.ps.emakers.API_PS.data.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByNameAndAutor(String nome, String autor);
}
