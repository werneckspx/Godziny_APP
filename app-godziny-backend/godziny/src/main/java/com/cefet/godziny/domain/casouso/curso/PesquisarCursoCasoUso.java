package com.cefet.godziny.domain.casouso.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.cefet.godziny.api.curso.CursoRecuperarDto;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
public class PesquisarCursoCasoUso {
    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @NotNull(message = "A sigla do curso é obrigatória")
    private String sigla;

    @NotNull(message = "O nome do curso é obrigatório")
    private String nome;
    
    public Page<CursoRecuperarDto> pesquisarCursos(Pageable pageable) {
        Specification<CursoEntidade> specification = Specification.where(null);
    
        if (nome != null) {
            specification = specification.and((root, query, criteriaBuilder) -> 
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
        }
        if (sigla != null) {
            specification = specification.and((root, query, criteriaBuilder) -> 
                criteriaBuilder.like(root.get("sigla"), "%" + sigla + "%"));
        }

        Page<CursoRecuperarDto> pageCursoRecuperarDto = cursoRepositorioJpa.listCursos(specification, pageable)
            .map(CursoRestConverter::EntidadeToCursoRecuperarDto);
        
        return pageCursoRecuperarDto;
    }

}