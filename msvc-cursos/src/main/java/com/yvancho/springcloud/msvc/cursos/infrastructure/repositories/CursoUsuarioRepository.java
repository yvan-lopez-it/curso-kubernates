package com.yvancho.springcloud.msvc.cursos.infrastructure.repositories;

import com.yvancho.springcloud.msvc.cursos.infrastructure.entity.CursoUsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface CursoUsuarioRepository extends CrudRepository<CursoUsuarioEntity, Long> {

    void deleteCursoUsuarioEntityByUsuarioId(Long usuarioId);

}
