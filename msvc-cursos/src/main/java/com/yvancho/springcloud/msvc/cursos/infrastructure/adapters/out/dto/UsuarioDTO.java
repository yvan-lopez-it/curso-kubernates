package com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.out.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String email;

    private String password;

}
