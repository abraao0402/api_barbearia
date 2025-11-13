package com.appbarbearia.appbarbearia.api.domain.model.controller;

import com.appbarbearia.appbarbearia.api.dto.BarbeiroRequest;
import com.appbarbearia.appbarbearia.api.dto.BarbeiroResponse;
import com.appbarbearia.appbarbearia.api.service.BarbeiroService;
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
@RequestMapping("/barbeiros")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @GetMapping
    public List<BarbeiroResponse> listar() {
        return barbeiroService.listar();
    }

    @GetMapping("/{barbeiroId}")
    public BarbeiroResponse buscarPorId(@PathVariable Long barbeiroId) {
        return barbeiroService.buscarPorId(barbeiroId);
    }

    @PostMapping
    public ResponseEntity<BarbeiroResponse> cadastrar(@Valid @RequestBody BarbeiroRequest request) {
        BarbeiroResponse response = barbeiroService.criar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{barbeiroId}")
    public BarbeiroResponse atualizar(@PathVariable Long barbeiroId,
                                      @Valid @RequestBody BarbeiroRequest request) {
        return barbeiroService.atualizar(barbeiroId, request);
    }

    @DeleteMapping("/{barbeiroId}")
    public ResponseEntity<Void> deletar(@PathVariable Long barbeiroId) {
        barbeiroService.excluir(barbeiroId);
        return ResponseEntity.noContent().build();
    }
}

