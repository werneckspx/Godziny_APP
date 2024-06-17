package com.cefet.godziny.infraestrutura.rest.curso;

import lombok.NoArgsConstructor;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.domain.casouso.curso.CriarCursoCasoUso;
import com.cefet.godziny.domain.casouso.curso.AtualizarCursoCasoUso;
import com.cefet.godziny.infraestrutura.exceptions.curso.CursoNaoEncontradoException;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CursoRestConverter {

    public static CursoEntidade OptionalToCursoEntidade(Optional<CursoEntidade> optional) throws Exception{
        if(!optional.isPresent()){
            throw new CursoNaoEncontradoException();
        }
        var cursoEntidade = new CursoEntidade();
        BeanUtils.copyProperties(optional.get(), cursoEntidade);
        return cursoEntidade;
    }

    public static CursoDto EntidadeToCursoDto(CursoEntidade entidade){
        if(entidade != null){
            return CursoDto.builder()
            .id(entidade.getId())
            .sigla(entidade.getSigla())
            .nome(entidade.getNome())
            .carga_horaria_complementar(entidade.getCarga_horaria_complementar())
            .build();
        }
        return null;
    }

    public static CursoEntidade DtoToEntidadeJpa(CursoDto dto) {
        return CursoEntidade.builder()
        .id(dto.getId())
        .sigla(dto.getSigla())
        .nome(dto.getNome())
        .carga_horaria_complementar(dto.getCarga_horaria_complementar())
        .build();
    }

    public static CriarCursoCasoUso DtoToCriarCursoCasoUso(CursoDto dto, CursoRepositorioJpa cursoRepositorioJpa) {
        return CriarCursoCasoUso.builder()
        .cursoRepositorioJpa(cursoRepositorioJpa)
        .sigla(dto.getSigla())
        .nome(dto.getNome())
        .cargaHorariaComplementar(dto.getCarga_horaria_complementar())
        .build();
    }

    public static AtualizarCursoCasoUso DtoToUpdateCursoCasoUso(String cursoSigla, CursoDto dto, CursoRepositorioJpa cursoRepositorioJpa) {
        return AtualizarCursoCasoUso.builder()
        .cursoRepositorioJpa(cursoRepositorioJpa)
        .id(dto.getId())
        .sigla(dto.getSigla())
        .siglaAntiga(cursoSigla)
        .nome(dto.getNome())
        .cargaHorariaComplementar(dto.getCarga_horaria_complementar())
        .build();
    }
}
