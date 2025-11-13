package com.appbarbearia.appbarbearia.api.service;

import com.appbarbearia.appbarbearia.api.domain.model.Barbeiro;
import com.appbarbearia.appbarbearia.api.dto.BarbeiroRequest;
import com.appbarbearia.appbarbearia.api.dto.BarbeiroResponse;
import com.appbarbearia.appbarbearia.api.exception.ResourceNotFoundException;
import com.appbarbearia.appbarbearia.domain.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    @Transactional(readOnly = true)
    public List<BarbeiroResponse> listar() {
        return barbeiroRepository.findAll()
                .stream()
                .map(BarbeiroResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public BarbeiroResponse buscarPorId(Long id) {
        return barbeiroRepository.findById(id)
                .map(BarbeiroResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Barbeiro", id));
    }

    @Transactional
    public BarbeiroResponse criar(BarbeiroRequest request) {
        Barbeiro barbeiro = new Barbeiro();
        aplicarDados(barbeiro, request);
        Barbeiro salvo = barbeiroRepository.save(barbeiro);
        return BarbeiroResponse.fromEntity(salvo);
    }

    @Transactional
    public BarbeiroResponse atualizar(Long id, BarbeiroRequest request) {
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Barbeiro", id));
        aplicarDados(barbeiro, request);
        return BarbeiroResponse.fromEntity(barbeiro);
    }

    @Transactional
    public void excluir(Long id) {
        if (!barbeiroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Barbeiro", id);
        }
        barbeiroRepository.deleteById(id);
    }

    private void aplicarDados(Barbeiro barbeiro, BarbeiroRequest request) {
        barbeiro.setNome(request.nome());
        barbeiro.setEspecialidade(request.especialidade());
    }
}

