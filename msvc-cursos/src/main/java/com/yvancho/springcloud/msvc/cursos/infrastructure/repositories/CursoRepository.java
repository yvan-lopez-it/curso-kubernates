package com.yvancho.springcloud.msvc.cursos.infrastructure.repositories;

import com.yvancho.springcloud.msvc.cursos.infrastructure.entity.CursoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<CursoEntity, Long> {

}
