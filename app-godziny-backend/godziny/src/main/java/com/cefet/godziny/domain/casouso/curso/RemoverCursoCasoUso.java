package com.cefet.godziny.domain.casouso.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.infraestrutura.exceptions.curso.RemoverCursoComUsuariosException;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class RemoverCursoCasoUso {
    @NotNull(message = "A sigla do curso é obrigatória")
    private String sigla;

    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    public void validarRemocao() throws Exception {
        CursoEntidade curso = cursoRepositorioJpa.pesquisarPorId(this.sigla);
        Pageable pageable = PageRequest.of(0, 1);
        Page<UsuarioEntidade> usuariosDoCurso = usuarioRepositorioJpa.listarUsuarioPorCurso(pageable, curso);
        if(!usuariosDoCurso.isEmpty()){
            throw new RemoverCursoComUsuariosException();
        }
    }
}
