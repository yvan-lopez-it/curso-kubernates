package com.yvancho.springcloud.msvc.usuarios.repositories;

import com.yvancho.springcloud.msvc.usuarios.models.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email=?1")
    Optional<Usuario> buscarPorEmail(String email);

    boolean existsByEmail(String email);
}
