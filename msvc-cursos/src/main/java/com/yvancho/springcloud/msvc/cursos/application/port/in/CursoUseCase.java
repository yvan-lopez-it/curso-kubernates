package com.yvancho.springcloud.msvc.cursos.application.port.in;

import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.CursoDTO;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface CursoUseCase {

    List<CursoDTO> listar();

    Optional<CursoDTO> getById(Long id);

    Optional<CursoDTO> porIdConUsuarios(Long id);

    CursoDTO guardar(CursoDTO cursoDto);

    void eliminar(Long id);

    void eliminarCursoUsuarioPorId(Long usuarioId);

    Optional<UsuarioDTO> asignarUsuario(UsuarioDTO usuarioDTO, Long cursoId);

    Optional<UsuarioDTO> crearUsuario(UsuarioDTO usuarioDTO, Long cursoId);

    Optional<UsuarioDTO> eliminarUsuario(UsuarioDTO usuarioDTO, Long cursoId);
}
