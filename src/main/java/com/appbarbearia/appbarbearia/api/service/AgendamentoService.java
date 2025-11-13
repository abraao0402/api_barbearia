package com.appbarbearia.appbarbearia.api.service;

import com.appbarbearia.appbarbearia.api.domain.model.Agendamento;
import com.appbarbearia.appbarbearia.api.domain.model.Barbeiro;
import com.appbarbearia.appbarbearia.api.domain.model.Servico;
import com.appbarbearia.appbarbearia.api.domain.model.Usuario;
import com.appbarbearia.appbarbearia.api.dto.AgendamentoRequest;
import com.appbarbearia.appbarbearia.api.dto.AgendamentoResponse;
import com.appbarbearia.appbarbearia.api.exception.BusinessException;
import com.appbarbearia.appbarbearia.api.exception.ResourceNotFoundException;
import com.appbarbearia.appbarbearia.domain.repository.AgendamentoRepository;
import com.appbarbearia.appbarbearia.domain.repository.BarbeiroRepository;
import com.appbarbearia.appbarbearia.domain.repository.ServicoRepository;
import com.appbarbearia.appbarbearia.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;

    @Transactional(readOnly = true)
    public List<AgendamentoResponse> listar() {
        return agendamentoRepository.findAll()
                .stream()
                .map(AgendamentoResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public AgendamentoResponse buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .map(AgendamentoResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento", id));
    }

    @Transactional
    public AgendamentoResponse criar(AgendamentoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", request.usuarioId()));
        Barbeiro barbeiro = barbeiroRepository.findById(request.barbeiroId())
                .orElseThrow(() -> new ResourceNotFoundException("Barbeiro", request.barbeiroId()));
        Servico servico = servicoRepository.findById(request.servicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Serviço", request.servicoId()));

        validarHorario(request.dataHoraInicio(), request.dataHoraFim());

        Agendamento agendamento = new Agendamento();
        aplicarDados(agendamento, request, usuario, barbeiro, servico);

        Agendamento salvo = agendamentoRepository.save(agendamento);
        return AgendamentoResponse.fromEntity(salvo);
    }

    @Transactional
    public AgendamentoResponse atualizar(Long id, AgendamentoRequest request) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento", id));

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", request.usuarioId()));
        Barbeiro barbeiro = barbeiroRepository.findById(request.barbeiroId())
                .orElseThrow(() -> new ResourceNotFoundException("Barbeiro", request.barbeiroId()));
        Servico servico = servicoRepository.findById(request.servicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Serviço", request.servicoId()));

        validarHorario(request.dataHoraInicio(), request.dataHoraFim());

        aplicarDados(agendamento, request, usuario, barbeiro, servico);
        return AgendamentoResponse.fromEntity(agendamento);
    }

    @Transactional
    public void excluir(Long id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agendamento", id);
        }
        agendamentoRepository.deleteById(id);
    }

    private void validarHorario(LocalDateTime inicio, LocalDateTime fim) {
        if (!fim.isAfter(inicio)) {
            throw new BusinessException("A data/hora final deve ser posterior à data/hora inicial.");
        }
    }

    private void aplicarDados(Agendamento agendamento,
                              AgendamentoRequest request,
                              Usuario usuario,
                              Barbeiro barbeiro,
                              Servico servico) {
        agendamento.setDataHoraInicio(request.dataHoraInicio());
        agendamento.setDataHoraFim(request.dataHoraFim());
        agendamento.setUsuario(usuario);
        agendamento.setBarbeiro(barbeiro);
        agendamento.setServico(servico);
    }
}

