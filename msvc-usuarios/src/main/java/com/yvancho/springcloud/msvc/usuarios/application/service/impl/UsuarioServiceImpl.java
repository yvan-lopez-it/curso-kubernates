package com.yvancho.springcloud.msvc.usuarios.application.service.impl;

import com.yvancho.springcloud.msvc.usuarios.adapter.in.dto.UsuarioDTO;
import com.yvancho.springcloud.msvc.usuarios.adapter.in.mapper.UsuarioDTOMapper;
import com.yvancho.springcloud.msvc.usuarios.adapter.out.UsuarioPersistenceAdapter;
import com.yvancho.springcloud.msvc.usuarios.application.service.UsuarioService;
import com.yvancho.springcloud.msvc.usuarios.domain.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioPersistenceAdapter adapter;
    private final UsuarioDTOMapper mapper;

    public UsuarioServiceImpl(UsuarioPersistenceAdapter adapter, UsuarioDTOMapper mapper) {
        this.adapter = adapter;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = adapter.listar();
        return usuarios.stream()
            .map(mapper::toDto)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        Optional<Usuario> o = adapter.getUsuarioById(id);
        return o.map(mapper::toDto);
    }

    @Override
    @Transactional
    public UsuarioDTO guardar(UsuarioDTO usuario) {
        Usuario usuarioGuardado = adapter.guardar(mapper.toDomain(usuario));
        return mapper.toDto(usuarioGuardado);
    }

    @Override
    public void eliminar(Long id) {
        adapter.eliminar(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByEmail(String email) {
        Optional<Usuario> o = adapter.findByEmail(email);
        return o.map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return adapter.existsByEmail(email);
    }

}
