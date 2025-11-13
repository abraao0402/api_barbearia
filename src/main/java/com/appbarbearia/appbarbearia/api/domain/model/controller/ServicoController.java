package com.appbarbearia.appbarbearia.api.domain.model.controller;

import com.appbarbearia.appbarbearia.api.dto.ServicoRequest;
import com.appbarbearia.appbarbearia.api.dto.ServicoResponse;
import com.appbarbearia.appbarbearia.api.service.ServicoService;
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
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping
    public List<ServicoResponse> listar() {
        return servicoService.listar();
    }

    @GetMapping("/{servicoId}")
    public ServicoResponse buscarPorId(@PathVariable Long servicoId) {
        return servicoService.buscarPorId(servicoId);
    }

    @PostMapping
    public ResponseEntity<ServicoResponse> cadastrar(@Valid @RequestBody ServicoRequest request) {
        ServicoResponse response = servicoService.criar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{servicoId}")
    public ServicoResponse atualizar(@PathVariable Long servicoId,
                                     @Valid @RequestBody ServicoRequest request) {
        return servicoService.atualizar(servicoId, request);
    }

    @DeleteMapping("/{servicoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long servicoId) {
        servicoService.excluir(servicoId);
        return ResponseEntity.noContent().build();
    }
}

