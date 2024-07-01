package com.yvancho.springcloud.msvc.usuarios.service.impl;

import com.yvancho.springcloud.msvc.usuarios.models.entity.Usuario;
import com.yvancho.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import com.yvancho.springcloud.msvc.usuarios.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> getUsuarioById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
    // return usuarioRepository.buscarPorEmail(String email);
        return repository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
