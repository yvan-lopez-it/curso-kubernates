package com.yvancho.springcloud.msvc.cursos.services;

import com.yvancho.springcloud.msvc.cursos.entity.Curso;
import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();

    Optional<Curso> getById(Long id);

    Curso guardar(Curso curso);

    void eliminar(Long id);

}
