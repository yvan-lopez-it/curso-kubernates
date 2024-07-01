package com.yvancho.springcloud.msvc.usuarios.application.service;

import com.yvancho.springcloud.msvc.usuarios.adapter.in.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioDTO> listar();

    Optional<UsuarioDTO> getUsuarioById(Long id);

    UsuarioDTO guardar(UsuarioDTO usuario);

    void eliminar(Long id);

    Optional<UsuarioDTO> findByEmail(String email);

    boolean existsByEmail(String email);

}
