package com.yvancho.springcloud.msvc.cursos.repositories;

import com.yvancho.springcloud.msvc.cursos.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {

}
