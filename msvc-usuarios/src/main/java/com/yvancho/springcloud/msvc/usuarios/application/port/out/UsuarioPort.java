package com.yvancho.springcloud.msvc.usuarios.application.port.out;

import com.yvancho.springcloud.msvc.usuarios.domain.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioPort {

    List<Usuario> listar();

    Optional<Usuario> getUsuarioById(Long id);

    Usuario guardar(Usuario usuario);

    void eliminar(Long id);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
