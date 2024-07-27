package com.cefet.godziny.domain.casouso.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.api.usuario.UsuarioRecuperarDto;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.usuario.UsuarioRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
public class PesquisarUsuarioCasoUso {
    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @NotNull(message = "A matricula do usuário é obrigatória")
    private Integer matricula;

    @NotNull(message = "A sigla do curso do usuário é obrigatória")
    private String cursoSigla;

    @NotNull(message = "O nome do usuário é obrigatório")
    private String nome;

    public CursoEntidade validarPesquisa() throws Exception {
        return cursoRepositorioJpa.findBySigla(cursoSigla);
    }

    public Page<UsuarioRecuperarDto> pesquisarUsuarios(Pageable pageable, CursoEntidade cursoEntidade) {
        Page<UsuarioRecuperarDto> pageUsuarioRecuperarDto = usuarioRepositorioJpa.listUsuarios(cursoEntidade, this.nome, this.matricula, pageable).map(entidade -> {
            return UsuarioRestConverter.EntidadeToUsuarioRecuperarDto(entidade);
        });
        return pageUsuarioRecuperarDto;
    }


/*    private UsuarioRecuperarDto createUsuarioRecuperarDto(UsuarioEntidade usuarioEntidade){
        UsuarioRecuperarDto dto = new  UsuarioRecuperarDto();
        dto.setMatricula(usuarioEntidade.getMatricula());
        dto.setCurso(CursoRestConverter.EntidadeToCursoRecuperarDto(usuarioEntidade.getCurso()));
        dto.setNome(usuarioEntidade.getNome());
        dto.setEmail(usuarioEntidade.getEmail());
        dto.setSenha(usuarioEntidade.getSenha());
        dto.setTipo(usuarioEntidade.getTipo());
        dto.getMatricula();
        dto.getCurso();
        dto.getNome();
        dto.getEmail();
        dto.getSenha();
        dto.getTipo();
        return dto;
    }*/
}