package com.ps.emakers.API_PS.exceptions.general;

public class EmprestimoReturnException extends RuntimeException {
    public EmprestimoReturnException(Long id) {
        super("Emprestimo id: " + id + "já devolvido.");
    }
}
