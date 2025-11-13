package com.appbarbearia.appbarbearia.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ServicoRequest(
        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotNull(message = "A duração é obrigatória.")
        @Positive(message = "A duração deve ser maior que zero.")
        Integer duracaoMinutos,

        @NotNull(message = "O preço é obrigatório.")
        @Positive(message = "O preço deve ser maior que zero.")
        BigDecimal preco
) {
}

