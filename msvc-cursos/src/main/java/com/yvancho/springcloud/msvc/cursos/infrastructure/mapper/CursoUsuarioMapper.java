package com.yvancho.springcloud.msvc.cursos.infrastructure.mapper;

import com.yvancho.springcloud.msvc.cursos.domain.model.CursoUsuario;
import com.yvancho.springcloud.msvc.cursos.infrastructure.entity.CursoUsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoUsuarioMapper {

    CursoUsuario toDomain(CursoUsuarioEntity entity);

    CursoUsuarioEntity toEntity(CursoUsuario model);
}
