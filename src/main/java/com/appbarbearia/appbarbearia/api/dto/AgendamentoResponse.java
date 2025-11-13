package com.appbarbearia.appbarbearia.api.dto;

import com.appbarbearia.appbarbearia.api.domain.model.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        UsuarioResponse usuario,
        BarbeiroResponse barbeiro,
        ServicoResponse servico
) {

    public static AgendamentoResponse fromEntity(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getDataHoraInicio(),
                agendamento.getDataHoraFim(),
                UsuarioResponse.fromEntity(agendamento.getUsuario()),
                BarbeiroResponse.fromEntity(agendamento.getBarbeiro()),
                ServicoResponse.fromEntity(agendamento.getServico())
        );
    }
}

