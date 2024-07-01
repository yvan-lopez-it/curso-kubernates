package com.yvancho.springcloud.msvc.usuarios.infrastructure.mapper;

import com.yvancho.springcloud.msvc.usuarios.domain.model.Usuario;
import com.yvancho.springcloud.msvc.usuarios.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity toEntity(Usuario usuario);

    Usuario toDomain(UsuarioEntity usuarioEntity);
}
