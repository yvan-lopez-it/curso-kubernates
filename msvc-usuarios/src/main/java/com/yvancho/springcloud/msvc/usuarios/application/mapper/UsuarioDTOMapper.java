package com.yvancho.springcloud.msvc.usuarios.application.mapper;

import com.yvancho.springcloud.msvc.usuarios.infrastructure.adapter.dto.UsuarioDTO;
import com.yvancho.springcloud.msvc.usuarios.domain.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDTOMapper {

    Usuario toDomain(UsuarioDTO usuarioDTO);

    UsuarioDTO toDto(Usuario usuario);

}
