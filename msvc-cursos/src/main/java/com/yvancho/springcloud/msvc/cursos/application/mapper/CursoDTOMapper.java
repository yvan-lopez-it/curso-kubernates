package com.yvancho.springcloud.msvc.cursos.application.mapper;

import com.yvancho.springcloud.msvc.cursos.domain.model.Curso;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.out.dto.CursoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoDTOMapper {

    CursoDTO toDto(Curso curso);

    Curso toDomain(CursoDTO cursoDto);

}
