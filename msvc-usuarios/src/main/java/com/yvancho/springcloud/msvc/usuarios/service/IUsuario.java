package com.yvancho.springcloud.msvc.usuarios.service;

import com.yvancho.springcloud.msvc.usuarios.models.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuario {

    List<Usuario> listar();

    Optional<Usuario> getUsuarioById(Long id);

    Usuario guardar(Usuario usuario);

    void eliminar(Long id);

}
