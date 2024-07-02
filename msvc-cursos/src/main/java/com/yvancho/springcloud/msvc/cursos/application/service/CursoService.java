package com.yvancho.springcloud.msvc.cursos.application.service;

import com.yvancho.springcloud.msvc.cursos.application.mapper.CursoDTOMapper;
import com.yvancho.springcloud.msvc.cursos.application.port.in.CursoUseCase;
import com.yvancho.springcloud.msvc.cursos.application.port.out.CursoPort;
import com.yvancho.springcloud.msvc.cursos.application.port.out.CursoUsuarioPort;
import com.yvancho.springcloud.msvc.cursos.domain.model.Curso;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.clients.UsuarioClientRest;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.CursoDTO;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.CursoUsuarioDTO;
import com.yvancho.springcloud.msvc.cursos.infrastructure.adapters.dto.UsuarioDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService implements CursoUseCase {

    private final CursoPort cursoPort;
    private final CursoUsuarioPort cursoUsuarioPort;
    private final CursoDTOMapper mapper;
    private final UsuarioClientRest clientRest;

    public CursoService(CursoPort cursoPort, CursoUsuarioPort cursoUsuarioPort, CursoDTOMapper mapper, UsuarioClientRest clientRest) {
        this.cursoPort = cursoPort;
        this.cursoUsuarioPort = cursoUsuarioPort;
        this.mapper = mapper;
        this.clientRest = clientRest;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoDTO> listar() {
        List<Curso> usuarios = cursoPort.listar();
        return usuarios.stream()
            .map(mapper::toDto)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CursoDTO> getById(Long id) {
        Optional<Curso> o = cursoPort.getById(id);
        return o.map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CursoDTO> porIdConUsuarios(Long id) {
        Optional<Curso> o = cursoPort.getById(id);
        if (o.isPresent()) {
            CursoDTO cursoDTO = mapper.toDto(o.get());
            if (!cursoDTO.getCursoUsuarios().isEmpty()) {
                List<Long> ids = cursoDTO.getCursoUsuarios().stream()
                    .map(CursoUsuarioDTO::getUsuarioId).toList();

                List<UsuarioDTO> usuarioDTOS = clientRest.obtenerAlumnosPorCurso(ids);
                cursoDTO.setUsuarios(usuarioDTOS);
            }
            return Optional.of(cursoDTO);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public CursoDTO guardar(CursoDTO cursoDto) {
        Curso usuarioGuardado = cursoPort.guardar(mapper.toDomain(cursoDto));
        return mapper.toDto(usuarioGuardado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoPort.eliminar(id);
    }

    @Override
    @Transactional
    public void eliminarCursoUsuarioPorId(Long usuarioId) {
        cursoUsuarioPort.eliminarCursoUsuarioPorId(usuarioId);
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> asignarUsuario(UsuarioDTO usuarioDTO, Long cursoId) {

        Optional<CursoDTO> o = cursoPort.getById(cursoId)
            .map(mapper::toDto);

        if (o.isPresent()) {
            UsuarioDTO usuarioMsvc = clientRest.detalle(usuarioDTO.getId());

            CursoDTO cursoDTO = o.get();
            CursoUsuarioDTO cursoUsuarioDTO = new CursoUsuarioDTO();
            cursoUsuarioDTO.setUsuarioId(usuarioMsvc.getId());

            cursoDTO.addCursoUsuario(cursoUsuarioDTO);

            cursoPort.guardar(mapper.toDomain(cursoDTO));

            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> crearUsuario(UsuarioDTO usuarioDTO, Long cursoId) {
        Optional<CursoDTO> o = cursoPort.getById(cursoId)
            .map(mapper::toDto);

        if (o.isPresent()) {
            UsuarioDTO usuarioNuevoMsvc = clientRest.crear(usuarioDTO);

            CursoDTO cursoDTO = o.get();
            CursoUsuarioDTO cursoUsuarioDTO = new CursoUsuarioDTO();
            cursoUsuarioDTO.setUsuarioId(usuarioNuevoMsvc.getId());

            cursoDTO.addCursoUsuario(cursoUsuarioDTO);

            cursoPort.guardar(mapper.toDomain(cursoDTO));

            return Optional.of(usuarioNuevoMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> eliminarUsuario(UsuarioDTO usuarioDTO, Long cursoId) {

        Optional<CursoDTO> o = cursoPort.getById(cursoId)
            .map(mapper::toDto);

        if (o.isPresent()) {
            UsuarioDTO usuarioMsvc = clientRest.detalle(usuarioDTO.getId());

            CursoDTO cursoDTO = o.get();
            CursoUsuarioDTO cursoUsuarioDTO = new CursoUsuarioDTO();
            cursoUsuarioDTO.setUsuarioId(usuarioMsvc.getId());

            cursoDTO.removeCursoUsuario(cursoUsuarioDTO);

            cursoPort.guardar(mapper.toDomain(cursoDTO));

            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }
}
