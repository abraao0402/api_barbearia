package com.appbarbearia.appbarbearia.api.dto;

import com.appbarbearia.appbarbearia.api.domain.model.Barbeiro;

public record BarbeiroResponse(
        Long id,
        String nome,
        String especialidade
) {

    public static BarbeiroResponse fromEntity(Barbeiro barbeiro) {
        return new BarbeiroResponse(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getEspecialidade()
        );
    }
}

