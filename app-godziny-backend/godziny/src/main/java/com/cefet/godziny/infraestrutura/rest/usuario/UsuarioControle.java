package com.cefet.godziny.infraestrutura.rest.usuario;

import org.springframework.web.bind.annotation.RestController;
import com.cefet.godziny.api.usuario.IUsuarioApi;
import com.cefet.godziny.api.usuario.UsuarioDto;
import com.cefet.godziny.api.usuario.UsuarioRecuperarDto;
import com.cefet.godziny.domain.casouso.usuario.CriarUsuarioCasoUso;
import com.cefet.godziny.domain.casouso.usuario.RemoverUsuarioCasoUso;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControle implements IUsuarioApi{

    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @Autowired
    private final PasswordEncoder enconder;

    @Override
    public ResponseEntity<UsuarioRecuperarDto> getUsuario(Integer matricula) throws Exception {
        var usuarioEntidade = usuarioRepositorioJpa.findById(matricula);
        var usuarioRecuperarDto = UsuarioRestConverter.EntidadeToUsuarioRecuperarDto(usuarioEntidade, CursoRestConverter.EntidadeToCursoDto(usuarioEntidade.getCurso()));
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRecuperarDto);
    }

    @Override
    public ResponseEntity<Page<UsuarioRecuperarDto>> listUsuario(Pageable pageable) throws Exception{
        Page<UsuarioRecuperarDto> pageUsuarioRecuperarDto = usuarioRepositorioJpa.listUsuario(pageable).map(entidade -> {
            return UsuarioRestConverter.EntidadeToUsuarioRecuperarDto(entidade, CursoRestConverter.EntidadeToCursoDto( entidade.getCurso()));
        });
        return ResponseEntity.status(HttpStatus.OK).body(pageUsuarioRecuperarDto);
    }

    @Override
    public ResponseEntity<Integer> createUsuario(@Valid UsuarioDto dto) throws Exception{
        CriarUsuarioCasoUso casoUso = UsuarioRestConverter.DtoToCriarUsuarioCasoUso(dto, usuarioRepositorioJpa);
        casoUso.validarCriacao();
        dto.setSenha(enconder.encode(dto.getSenha()));
        UsuarioEntidade usuarioEntidade = UsuarioRestConverter.DtoToEntidadeJpa(dto, cursoRepositorioJpa.findyById(dto.getCursoId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepositorioJpa.createUsuario(usuarioEntidade));
    }

    @Override
    public ResponseEntity<Integer> updateUsuario(@Valid UsuarioDto dto) throws Exception {
        CriarUsuarioCasoUso casoUso = UsuarioRestConverter.DtoToCriarUsuarioCasoUso(dto, usuarioRepositorioJpa);
        casoUso.validarCriacao();
        dto.setSenha(enconder.encode(dto.getSenha()));
        UsuarioEntidade usuarioEntidade = UsuarioRestConverter.DtoToEntidadeJpa(dto, cursoRepositorioJpa.findyById(dto.getCursoId()));
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepositorioJpa.updateUsuario(usuarioEntidade));
    }

    @Override
    public ResponseEntity<Void> removeUsuario(Integer matricula) throws Exception {
        RemoverUsuarioCasoUso casoUso = new RemoverUsuarioCasoUso(matricula);
        casoUso.validarRemocao();
        usuarioRepositorioJpa.deleteUsuario(matricula);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
