package com.appbarbearia.appbarbearia.api.domain.model.controller;

import com.appbarbearia.appbarbearia.api.dto.AgendamentoRequest;
import com.appbarbearia.appbarbearia.api.dto.AgendamentoResponse;
import com.appbarbearia.appbarbearia.api.service.AgendamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoResponse> listar() {
        return agendamentoService.listar();
    }

    @GetMapping("/{agendamentoId}")
    public AgendamentoResponse buscarPorId(@PathVariable Long agendamentoId) {
        return agendamentoService.buscarPorId(agendamentoId);
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponse> cadastrar(@Valid @RequestBody AgendamentoRequest request) {
        AgendamentoResponse response = agendamentoService.criar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{agendamentoId}")
    public AgendamentoResponse atualizar(@PathVariable Long agendamentoId,
                                         @Valid @RequestBody AgendamentoRequest request) {
        return agendamentoService.atualizar(agendamentoId, request);
    }

    @DeleteMapping("/{agendamentoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long agendamentoId) {
        agendamentoService.excluir(agendamentoId);
        return ResponseEntity.noContent().build();
    }
}

