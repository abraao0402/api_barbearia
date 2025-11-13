package com.appbarbearia.appbarbearia.domain.repository;

import com.appbarbearia.appbarbearia.api.domain.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
