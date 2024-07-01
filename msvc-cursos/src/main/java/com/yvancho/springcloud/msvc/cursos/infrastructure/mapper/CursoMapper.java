package com.yvancho.springcloud.msvc.cursos.infrastructure.mapper;

import com.yvancho.springcloud.msvc.cursos.domain.model.Curso;
import com.yvancho.springcloud.msvc.cursos.infrastructure.entity.CursoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoEntity toEntity(Curso curso);

    Curso toDomain(CursoEntity cursoEntity);

}
