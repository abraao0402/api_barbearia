package com.appbarbearia.appbarbearia.domain.repository;

import com.appbarbearia.appbarbearia.api.domain.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
}
