package com.ps.emakers.API_PS.exceptions.general;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("Entidade de id:" + id + " não encontrada");
    }
}
