package com.yvancho.springcloud.msvc.usuarios.infrastructure.repositories;

import com.yvancho.springcloud.msvc.usuarios.infrastructure.entity.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.email=?1")
    Optional<UsuarioEntity> buscarPorEmail(String email);

    boolean existsByEmail(String email);
}
