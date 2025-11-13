package com.appbarbearia.appbarbearia.api.service;

import com.appbarbearia.appbarbearia.api.domain.model.Servico;
import com.appbarbearia.appbarbearia.api.dto.ServicoRequest;
import com.appbarbearia.appbarbearia.api.dto.ServicoResponse;
import com.appbarbearia.appbarbearia.api.exception.ResourceNotFoundException;
import com.appbarbearia.appbarbearia.domain.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    @Transactional(readOnly = true)
    public List<ServicoResponse> listar() {
        return servicoRepository.findAll()
                .stream()
                .map(ServicoResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ServicoResponse buscarPorId(Long id) {
        return servicoRepository.findById(id)
                .map(ServicoResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço", id));
    }

    @Transactional
    public ServicoResponse criar(ServicoRequest request) {
        Servico servico = new Servico();
        aplicarDados(servico, request);
        Servico salvo = servicoRepository.save(servico);
        return ServicoResponse.fromEntity(salvo);
    }

    @Transactional
    public ServicoResponse atualizar(Long id, ServicoRequest request) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço", id));
        aplicarDados(servico, request);
        return ServicoResponse.fromEntity(servico);
    }

    @Transactional
    public void excluir(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Serviço", id);
        }
        servicoRepository.deleteById(id);
    }

    private void aplicarDados(Servico servico, ServicoRequest request) {
        servico.setNome(request.nome());
        servico.setDuracaoMinutos(request.duracaoMinutos());
        servico.setPreco(request.preco());
    }
}

