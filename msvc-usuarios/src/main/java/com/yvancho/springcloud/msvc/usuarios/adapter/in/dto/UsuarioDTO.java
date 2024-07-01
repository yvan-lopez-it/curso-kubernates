package com.yvancho.springcloud.msvc.usuarios.adapter.in.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String nombre;

    private String email;

    private String password;

}
