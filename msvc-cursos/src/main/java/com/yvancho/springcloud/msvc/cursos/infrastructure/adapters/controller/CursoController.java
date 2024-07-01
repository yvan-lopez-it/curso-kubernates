package com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.controller;

import com.yvancho.springcloud.msvc.cursos.application.port.in.CursoUseCase;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.CursoDTO;

import jakarta.validation.Valid;
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
public class CursoController {

    private final CursoUseCase useCase;

    public CursoController(CursoUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listar() {
        return ResponseEntity.ok(useCase.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@Valid @PathVariable Long id) {
        Optional<CursoDTO> o = useCase.getById(id);

        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody CursoDTO cursoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return this.validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(useCase.guardar(cursoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody CursoDTO cursoDTO, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<CursoDTO> o = useCase.getById(id);
        if (o.isPresent()) {
            CursoDTO cursoDb = o.get();
            cursoDb.setNombre(cursoDTO.getNombre());

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(useCase.guardar(cursoDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@Valid @PathVariable Long id) {
        Optional<CursoDTO> o = useCase.getById(id);
        if (o.isPresent()) {
            useCase.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(
            err.getField(),
            "El campo " + err.getField() + " " + err.getDefaultMessage()
        ));
        return ResponseEntity.badRequest().body(errores);
    }
}
