package com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.persistence;

import com.yvancho.springcloud.msvc.cursos.application.port.out.CursoPort;
import com.yvancho.springcloud.msvc.cursos.domain.model.Curso;
import com.yvancho.springcloud.msvc.cursos.infrastructure.entity.CursoEntity;
import com.yvancho.springcloud.msvc.cursos.infrastructure.mapper.CursoMapper;
import com.yvancho.springcloud.msvc.cursos.infrastructure.repositories.CursoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class CursoPersistenceAdapter implements CursoPort {

    private final CursoRepository repository;
    private final CursoMapper mapper;

    public CursoPersistenceAdapter(CursoRepository repository, CursoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Curso> listar() {
        List<CursoEntity> entities = (List<CursoEntity>) repository.findAll();
        return entities.stream()
            .map(mapper::toDomain)
            .toList();
    }

    @Override
    public Optional<Curso> getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDomain);
    }

    @Override
    public Curso guardar(Curso curso) {
        CursoEntity cursoEntity = mapper.toEntity(curso);
        return mapper.toDomain(repository.save(cursoEntity));
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
