package com.yvancho.springcloud.msvc.usuarios.infrastructure.adapter.persistence;

import com.yvancho.springcloud.msvc.usuarios.application.port.out.UsuarioPort;
import com.yvancho.springcloud.msvc.usuarios.domain.model.Usuario;
import com.yvancho.springcloud.msvc.usuarios.infrastructure.entity.UsuarioEntity;
import com.yvancho.springcloud.msvc.usuarios.infrastructure.mapper.UsuarioMapper;
import com.yvancho.springcloud.msvc.usuarios.infrastructure.repositories.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPersistenceAdapter implements UsuarioPort {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioPersistenceAdapter(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Usuario> listar() {
        List<UsuarioEntity> entities = (List<UsuarioEntity>) repository.findAll();
        return entities.stream()
            .map(mapper::toDomain)
            .toList();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return repository.findById(id)
            .map(mapper::toDomain);
    }

    public Usuario guardar(Usuario usuario) {
        UsuarioEntity usuarioEntity = mapper.toEntity(usuario);
        return mapper.toDomain(repository.save(usuarioEntity));
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email)
            .map(mapper::toDomain);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

}
