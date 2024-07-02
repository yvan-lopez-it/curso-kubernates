package com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.clients;

import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-usuarios", url = "localhost:8001")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    UsuarioDTO detalle(@PathVariable Long id);

    @PostMapping
    UsuarioDTO crear(@RequestBody UsuarioDTO usuarioDTO);
}
