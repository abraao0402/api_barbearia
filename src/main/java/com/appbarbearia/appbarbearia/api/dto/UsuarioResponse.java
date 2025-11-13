package com.appbarbearia.appbarbearia.api.dto;

import com.appbarbearia.appbarbearia.api.domain.model.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String telefone,
        String email
) {

    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getEmail()
        );
    }
}

