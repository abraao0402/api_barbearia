package com.appbarbearia.appbarbearia.api.dto;

import com.appbarbearia.appbarbearia.api.domain.model.Servico;

import java.math.BigDecimal;

public record ServicoResponse(
        Long id,
        String nome,
        Integer duracaoMinutos,
        BigDecimal preco
) {

    public static ServicoResponse fromEntity(Servico servico) {
        return new ServicoResponse(
                servico.getId(),
                servico.getNome(),
                servico.getDuracaoMinutos(),
                servico.getPreco()
        );
    }
}

