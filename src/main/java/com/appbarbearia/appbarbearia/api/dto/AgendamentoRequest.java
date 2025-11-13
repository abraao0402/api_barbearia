package com.appbarbearia.appbarbearia.api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequest(
        @NotNull(message = "A data/hora inicial é obrigatória.")
        @FutureOrPresent(message = "A data/hora inicial deve ser no presente ou futuro.")
        LocalDateTime dataHoraInicio,

        @NotNull(message = "A data/hora final é obrigatória.")
        @Future(message = "A data/hora final deve ser no futuro.")
        LocalDateTime dataHoraFim,

        @NotNull(message = "O usuário é obrigatório.")
        Long usuarioId,

        @NotNull(message = "O barbeiro é obrigatório.")
        Long barbeiroId,

        @NotNull(message = "O serviço é obrigatório.")
        Long servicoId
) {
}

