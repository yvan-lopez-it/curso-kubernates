package com.yvancho.springcloud.msvc.usuarios.application.service;

import com.yvancho.springcloud.msvc.usuarios.infrastructure.adapter.dto.UsuarioDTO;
import com.yvancho.springcloud.msvc.usuarios.application.mapper.UsuarioDTOMapper;
import com.yvancho.springcloud.msvc.usuarios.application.port.in.UsuarioUseCase;
import com.yvancho.springcloud.msvc.usuarios.application.port.out.UsuarioPort;
import com.yvancho.springcloud.msvc.usuarios.domain.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UsuarioUseCase {

    private final UsuarioPort usuarioPort;
    private final UsuarioDTOMapper mapper;

    public UsuarioService(UsuarioPort usuarioPort, UsuarioDTOMapper mapper) {
        this.usuarioPort = usuarioPort;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = usuarioPort.listar();
        return usuarios.stream()
            .map(mapper::toDto)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        Optional<Usuario> o = usuarioPort.getUsuarioById(id);
        return o.map(mapper::toDto);
    }

    @Override
    @Transactional
    public UsuarioDTO guardar(UsuarioDTO usuario) {
        Usuario usuarioGuardado = usuarioPort.guardar(mapper.toDomain(usuario));
        return mapper.toDto(usuarioGuardado);
    }

    @Override
    public void eliminar(Long id) {
        usuarioPort.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByEmail(String email) {
        Optional<Usuario> o = usuarioPort.findByEmail(email);
        return o.map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return usuarioPort.existsByEmail(email);
    }

}
