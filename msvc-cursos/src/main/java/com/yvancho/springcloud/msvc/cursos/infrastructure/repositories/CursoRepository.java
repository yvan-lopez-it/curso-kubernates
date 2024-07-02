package com.yvancho.springcloud.msvc.cursos.infrastructure.repositories;

import com.yvancho.springcloud.msvc.cursos.infrastructure.entity.CursoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<CursoEntity, Long> {

    @Modifying
    @Query("DELETE FROM CursoUsuarioEntity as cu WHERE cu.usuarioId=?1")
    void eliminarCursoUsuarioPorId(Long usuarioId);

}
