package com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.out.persistence;

import com.yvancho.springcloud.msvc.cursos.application.port.out.CursoUsuarioPort;
import com.yvancho.springcloud.msvc.cursos.infrastructure.repositories.CursoUsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class CursoUsuarioPersistenceAdapter implements CursoUsuarioPort {

    private final CursoUsuarioRepository repository;

    public CursoUsuarioPersistenceAdapter(CursoUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void eliminarCursoUsuarioPorId(Long usuarioId) {
        repository.deleteCursoUsuarioEntityByUsuarioId(usuarioId);
    }
}
