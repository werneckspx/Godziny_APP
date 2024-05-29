package com.cefet.godziny.domain.casouso.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.api.usuario.UsuarioRecuperarDto;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;
import com.cefet.godziny.infraestrutura.rest.usuario.UsuarioRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
public class ListarUsuarioCasoUso {
    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    @NotNull(message = "A matricula do usuário é obrigatória")
    private Integer matricula;

    public UsuarioRecuperarDto validarListagem() throws Exception {
        UsuarioEntidade usuarioEntidade = usuarioRepositorioJpa.findById(matricula);
        return UsuarioRestConverter.EntidadeToUsuarioRecuperarDto(usuarioEntidade, CursoRestConverter.EntidadeToCursoDto(usuarioEntidade.getCurso()));
    }

    public Page<UsuarioRecuperarDto> listarUsuarios(Pageable pageable) {
        Page<UsuarioRecuperarDto> pageUsuarioRecuperarDto = usuarioRepositorioJpa.listUsuarios(pageable).map(entidade -> {
            return UsuarioRestConverter.EntidadeToUsuarioRecuperarDto(entidade, CursoRestConverter.EntidadeToCursoDto(entidade.getCurso()));
        });
        return pageUsuarioRecuperarDto;
    }
}