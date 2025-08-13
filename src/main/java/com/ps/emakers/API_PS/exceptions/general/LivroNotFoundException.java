package com.ps.emakers.API_PS.exceptions.general;

public class LivroNotFoundException extends RuntimeException {
    public LivroNotFoundException(Long id) {
        super("Livro de id:" + id + " não encontrado");
    }
}
