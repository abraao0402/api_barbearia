package com.appbarbearia.appbarbearia.domain.repository;

import com.appbarbearia.appbarbearia.api.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
