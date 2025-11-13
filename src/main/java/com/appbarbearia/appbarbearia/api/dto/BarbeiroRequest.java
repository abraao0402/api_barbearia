package com.appbarbearia.appbarbearia.api.dto;

import jakarta.validation.constraints.NotBlank;

public record BarbeiroRequest(
        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank(message = "A especialidade é obrigatória.")
        String especialidade
) {
}

