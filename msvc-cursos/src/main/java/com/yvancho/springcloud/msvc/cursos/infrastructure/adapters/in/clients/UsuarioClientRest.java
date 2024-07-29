package com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.in.clients;

import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.out.dto.UsuarioDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-usuarios")
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    UsuarioDTO detalle(@PathVariable Long id);

    @PostMapping
    UsuarioDTO crear(@RequestBody UsuarioDTO usuarioDTO);

    @GetMapping("/usuarios-por-curso")
    List<UsuarioDTO> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
}
