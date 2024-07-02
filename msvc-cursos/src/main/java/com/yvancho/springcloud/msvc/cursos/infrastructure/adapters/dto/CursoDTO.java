package com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CursoDTO {

    private Long id;

    private String nombre;

    private List<CursoUsuarioDTO> cursoUsuarios;

    private List<UsuarioDTO> usuarios;

    public CursoDTO() {
        this.cursoUsuarios = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void addCursoUsuario(CursoUsuarioDTO cursoUsuarioDTO) {
        this.cursoUsuarios.add(cursoUsuarioDTO);
    }

    public void removeCursoUsuario(CursoUsuarioDTO cursoUsuarioDTO) {
        this.cursoUsuarios.remove(cursoUsuarioDTO);
    }

}
