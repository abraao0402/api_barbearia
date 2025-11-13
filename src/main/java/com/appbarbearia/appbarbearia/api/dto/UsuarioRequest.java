package com.appbarbearia.appbarbearia.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioRequest(
        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "O telefone é obrigatório.")
        @Pattern(regexp = "\\+?[0-9\\s()-]{8,20}", message = "Informe um telefone válido.")
        String telefone,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        String email
) {
}

