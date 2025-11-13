package com.appbarbearia.appbarbearia.domain.repository;

import com.appbarbearia.appbarbearia.api.domain.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
