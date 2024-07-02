package com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoUsuarioDTO {

    private Long id;

    private Long usuarioId;

    // equals y hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CursoUsuarioDTO)) {
            return false;
        }

        CursoUsuarioDTO o = (CursoUsuarioDTO) obj;
        return this.usuarioId != null && this.usuarioId.equals(o.usuarioId);

    }
}
