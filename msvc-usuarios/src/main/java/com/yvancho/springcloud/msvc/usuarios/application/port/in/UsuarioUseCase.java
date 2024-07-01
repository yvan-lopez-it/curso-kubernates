package com.yvancho.springcloud.msvc.usuarios.application.port.in;

import com.yvancho.springcloud.msvc.usuarios.infrastructure.adapter.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface UsuarioUseCase {

    List<UsuarioDTO> listar();

    Optional<UsuarioDTO> getUsuarioById(Long id);

    UsuarioDTO guardar(UsuarioDTO usuario);

    void eliminar(Long id);

    Optional<UsuarioDTO> findByEmail(String email);

    boolean existsByEmail(String email);
}
