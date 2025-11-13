package com.appbarbearia.appbarbearia.api.service;

import com.appbarbearia.appbarbearia.api.domain.model.Usuario;
import com.appbarbearia.appbarbearia.api.dto.UsuarioRequest;
import com.appbarbearia.appbarbearia.api.dto.UsuarioResponse;
import com.appbarbearia.appbarbearia.api.exception.ResourceNotFoundException;
import com.appbarbearia.appbarbearia.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioResponse::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
    }

    @Transactional
    public UsuarioResponse criar(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        aplicarDados(usuario, request);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioResponse.fromEntity(salvo);
    }

    @Transactional
    public UsuarioResponse atualizar(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id));
        aplicarDados(usuario, request);
        return UsuarioResponse.fromEntity(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário", id);
        }
        usuarioRepository.deleteById(id);
    }

    private void aplicarDados(Usuario usuario, UsuarioRequest request) {
        usuario.setNome(request.nome());
        usuario.setTelefone(request.telefone());
        usuario.setEmail(request.email());
    }
}

