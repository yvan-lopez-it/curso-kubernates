package com.yvancho.springcloud.msvc.cursos.application.port.out;

import com.yvancho.springcloud.msvc.cursos.domain.model.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoPort {

    List<Curso> listar();

    Optional<Curso> getById(Long id);

    Curso guardar(Curso curso);

    void eliminar(Long id);

}
