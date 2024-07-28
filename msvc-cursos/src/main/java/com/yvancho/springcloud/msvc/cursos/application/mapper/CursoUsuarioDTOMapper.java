package com.yvancho.springcloud.msvc.cursos.application.mapper;

import com.yvancho.springcloud.msvc.cursos.domain.model.CursoUsuario;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.out.dto.CursoUsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoUsuarioDTOMapper {
    CursoUsuarioDTO toDTO(CursoUsuario model);

    CursoUsuario toDomain(CursoUsuarioDTO dto);
}
