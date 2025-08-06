package com.ps.emakers.API_PS.data.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PessoaRequestDTO(

        @NotBlank(message = "Nome obrigatório")
        String name,

        @Pattern(regexp = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}|\\d{11})$", message = "Formato de CPF inválido.Use 000.000.000-00 ou 11 digitos")
        String cpf,

        @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP inválido. Use o formato 00000-000 ou 00000000")
        String cep,

        @NotBlank(message = "Email obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatória")
        String senha
) {
}

