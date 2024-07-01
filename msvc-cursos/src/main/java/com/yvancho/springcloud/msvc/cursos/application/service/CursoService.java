package com.yvancho.springcloud.msvc.cursos.application.service;

import com.yvancho.springcloud.msvc.cursos.application.mapper.CursoDTOMapper;
import com.yvancho.springcloud.msvc.cursos.application.port.in.CursoUseCase;
import com.yvancho.springcloud.msvc.cursos.application.port.out.CursoPort;
import com.yvancho.springcloud.msvc.cursos.domain.model.Curso;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.CursoDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService implements CursoUseCase {

    private final CursoPort cursoPort;
    private final CursoDTOMapper mapper;

    public CursoService(CursoPort cursoPort, CursoDTOMapper mapper) {
        this.cursoPort = cursoPort;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDTO> listar() {
        List<Curso> usuarios = cursoPort.listar();
        return usuarios.stream()
            .map(mapper::toDto)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CursoDTO> getById(Long id) {
        Optional<Curso> o = cursoPort.getById(id);
        return o.map(mapper::toDto);
    }

    @Override
    @Transactional
    public CursoDTO guardar(CursoDTO cursoDto) {
        Curso usuarioGuardado = cursoPort.guardar(mapper.toDomain(cursoDto));
        return mapper.toDto(usuarioGuardado);
    }

    @Override
    public void eliminar(Long id) {
        cursoPort.eliminar(id);
    }
}
