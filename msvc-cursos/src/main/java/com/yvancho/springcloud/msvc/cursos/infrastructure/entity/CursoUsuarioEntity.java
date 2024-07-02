package com.yvancho.springcloud.msvc.cursos.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Data;

@Data
@Entity
@Table(name = "cursos_usuarios")
public class CursoUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", unique = true)
    private Long usuarioId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CursoUsuarioEntity)) {
            return false;
        }

        CursoUsuarioEntity o = (CursoUsuarioEntity) obj;
        return this.usuarioId != null && this.usuarioId.equals(o.usuarioId);

    }

}
