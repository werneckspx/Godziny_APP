package com.cefet.godziny.domain.casouso.curso;

import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.api.curso.CursoRecuperarDto;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.usuario.UsuarioRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
public class ListarCursoCasoUso {
    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @NotNull(message = "A sigla do curso é obrigatória")
    private String sigla;

    public CursoRecuperarDto validarListagem() throws Exception {
        CursoEntidade entidade = cursoRepositorioJpa.findBySigla(this.sigla);
        createCursoDto(entidade);
        return createCursoRecuperarDto(entidade);
    }
    
    private CursoDto createCursoDto(CursoEntidade cursoEntidade){
        CursoDto dto = new  CursoDto();
        dto.setId(cursoEntidade.getId());
        dto.setSigla(cursoEntidade.getSigla());
        dto.setNome(cursoEntidade.getNome());
        dto.setCarga_horaria_complementar(cursoEntidade.getCarga_horaria_complementar());
        dto.setCoordenador_matricula(cursoEntidade.getCoordenador().getMatricula());
        dto.getId();
        dto.getSigla();
        dto.getNome();
        dto.getCarga_horaria_complementar();
        dto.getCoordenador_matricula();
        return dto;
    }

    private CursoRecuperarDto createCursoRecuperarDto(CursoEntidade cursoEntidade){
        CursoRecuperarDto dto = new CursoRecuperarDto();
        dto.setId(cursoEntidade.getId());
        dto.setSigla(cursoEntidade.getSigla());
        dto.setNome(cursoEntidade.getNome());
        dto.setCarga_horaria_complementar(cursoEntidade.getCarga_horaria_complementar());
        dto.setCoordenador(UsuarioRestConverter.EntidadeToUsuarioRecuperarDto(cursoEntidade.getCoordenador()));
        dto.getId();
        dto.getSigla();
        dto.getNome();
        dto.getCarga_horaria_complementar();
        dto.getCoordenador();
        return dto;
    }
}


