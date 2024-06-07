package com.cefet.godziny.infraestrutura.rest.categoria;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import com.cefet.godziny.api.categoria.CategoriaDto;
import com.cefet.godziny.api.categoria.CategoriaRecuperarDto;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.infraestrutura.exceptions.categoria.CategoriaNaoEncontradaException;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;

public class CategoriaRestConverter {
    public static CategoriaEntidade OptionalToCategoriaEntidade(Optional<CategoriaEntidade> optional) throws Exception {
        if(!optional.isPresent()){
            throw new CategoriaNaoEncontradaException();
        }
        var categoriaEntidade = new CategoriaEntidade();
        BeanUtils.copyProperties(optional.get(), categoriaEntidade);
        return categoriaEntidade;
    }

     public static CategoriaRecuperarDto EntidadeToUsuarioRecuperarDto(CategoriaEntidade categoriaEntidade, CursoDto cursoDto){
        return CategoriaRecuperarDto.builder()
        .id(categoriaEntidade.getId())
        .curso(cursoDto)
        .nome(categoriaEntidade.getNome())
        .horas_maximas(categoriaEntidade.getHoras_maximas())
        .horas_multiplicador(categoriaEntidade.getHoras_multiplicador())
        .descricao(categoriaEntidade.getDescricao())
        .build();
    }

    public static CategoriaEntidade DtoToEntidadeJpa (CategoriaDto dto, CursoEntidade cursoEntidade){
        return CategoriaEntidade.builder()
        .id(dto.getId())
        .curso(cursoEntidade)
        .nome(dto.getNome())
        .horas_maximas(dto.getHoras_maximas())
        .horas_multiplicador(dto.getHoras_multiplicador())
        .descricao(dto.getDescricao())
        .build();
    }

    // public static CriarUsuarioCasoUso DtoToCriarUsuarioCasoUso(UsuarioDto dto, UsuarioRepositorioJpa usuarioRepositorioJpa, CursoRepositorioJpa cursoRepositorioJpa){
    //     return CriarUsuarioCasoUso.builder()
    //     .nome(dto.getNome())
    //     .email(dto.getEmail())
    //     .senha(dto.getSenha())
    //     .usuarioRepositorioJpa(usuarioRepositorioJpa)
    //     .cursoRepositorioJpa(cursoRepositorioJpa)
    //     .build();
    // }

    
    // public static AtualizarUsuarioCasoUso DtoToUpdateCursoCasoUso(UsuarioDto dto, UsuarioRepositorioJpa usuarioRepositorioJpa, CursoRepositorioJpa cursoRepositorioJpa){
    //     return AtualizarUsuarioCasoUso.builder()
    //     .nome(dto.getNome())
    //     .email(dto.getEmail())
    //     .senha(dto.getSenha())
    //     .usuarioRepositorioJpa(usuarioRepositorioJpa)
    //     .cursoRepositorioJpa(cursoRepositorioJpa)
    //     .build();
    // }
}
