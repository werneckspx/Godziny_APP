package com.cefet.godziny.domain.casouso.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ListarCursoCasoUso {
    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @NotNull(message = "A sigla do curso é obrigatória")
    private String sigla;

    public CursoDto validarListagem() throws Exception {
        return CursoRestConverter.EntidadeToCursoDto(cursoRepositorioJpa.findBySigla(this.sigla));
    }

    public Page<CursoDto> ListarCursos(Pageable pageable) {
        Page<CursoDto> pageCursoDto = cursoRepositorioJpa.listCursos(pageable).map(CursoRestConverter::EntidadeToCursoDto);
        return pageCursoDto;
    }
}