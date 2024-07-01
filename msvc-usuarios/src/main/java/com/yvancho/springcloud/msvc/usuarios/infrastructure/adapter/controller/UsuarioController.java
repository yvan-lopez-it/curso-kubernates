package com.yvancho.springcloud.msvc.usuarios.infrastructure.adapter.controller;

import com.yvancho.springcloud.msvc.usuarios.infrastructure.adapter.dto.UsuarioDTO;

import com.yvancho.springcloud.msvc.usuarios.application.port.in.UsuarioUseCase;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    private final UsuarioUseCase useCase;

    public UsuarioController(UsuarioUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(useCase.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@Valid @PathVariable Long id) {
        Optional<UsuarioDTO> o = useCase.getUsuarioById(id);

        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        if (!usuarioDTO.getEmail().isEmpty()
            && useCase.existsByEmail(usuarioDTO.getEmail())) {
            return ResponseEntity.badRequest()
                .body(Collections
                    .singletonMap("message", "Ya existe un usuario con ese email."));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(useCase.guardar(usuarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody UsuarioDTO usuarioRequest, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<UsuarioDTO> o = useCase.getUsuarioById(id);

        if (o.isPresent()) {
            UsuarioDTO usuarioDTO = o.get();

            if (!usuarioRequest.getEmail().isEmpty()
                && !usuarioRequest.getEmail().equalsIgnoreCase(usuarioDTO.getEmail())
                && useCase.findByEmail(usuarioRequest.getEmail()).isPresent()) {

                return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message", "Ya existe un usuario con ese email."));
            }

            usuarioDTO.setNombre(usuarioRequest.getNombre());
            usuarioDTO.setEmail(usuarioRequest.getEmail());
            usuarioDTO.setPassword(usuarioRequest.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(useCase.guardar(usuarioDTO));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@Valid @PathVariable Long id) {
        Optional<UsuarioDTO> usuario = useCase.getUsuarioById(id);
        if (usuario.isPresent()) {
            useCase.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        result.getFieldErrors()
            .forEach(err -> errores.put(
                err.getField(),
                "El campo " + err.getField() + " " + err.getDefaultMessage()
            ));

        return ResponseEntity.badRequest().body(errores);
    }
}
