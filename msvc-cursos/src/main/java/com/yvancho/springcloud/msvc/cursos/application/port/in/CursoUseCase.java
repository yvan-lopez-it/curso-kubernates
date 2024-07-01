package com.yvancho.springcloud.msvc.cursos.application.port.in;

import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.CursoDTO;
import java.util.List;
import java.util.Optional;

public interface CursoUseCase {

    List<CursoDTO> listar();

    Optional<CursoDTO> getById(Long id);

    CursoDTO guardar(CursoDTO cursoDto);

    void eliminar(Long id);
}
