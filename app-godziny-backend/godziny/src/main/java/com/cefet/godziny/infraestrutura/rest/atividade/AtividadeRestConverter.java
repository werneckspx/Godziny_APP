package com.cefet.godziny.infraestrutura.rest.atividade;

import java.util.Optional;
import org.springframework.beans.BeanUtils;

import com.cefet.godziny.api.atividade.AtividadeDto;
import com.cefet.godziny.api.atividade.AtividadeRecuperarDto;
import com.cefet.godziny.infraestrutura.exceptions.atividade.AtividadeNaoEncontradaException;
import com.cefet.godziny.infraestrutura.persistencia.atividade.AtividadeEntidade;
import com.cefet.godziny.infraestrutura.persistencia.atividade.arquivo.ArquivoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.rest.atividade.arquivo.ArquivoRestConverter;
import com.cefet.godziny.infraestrutura.rest.categoria.CategoriaRestConverter;
import com.cefet.godziny.infraestrutura.rest.usuario.UsuarioRestConverter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AtividadeRestConverter {
     public static AtividadeEntidade OptionalToAtividadeEntidade(Optional<AtividadeEntidade> optional) throws Exception {
        if(!optional.isPresent()){
            throw new AtividadeNaoEncontradaException();
        }
        var atividadeEntidade = new AtividadeEntidade();
        BeanUtils.copyProperties(optional.get(), atividadeEntidade);
        return atividadeEntidade;
    }

    public static AtividadeRecuperarDto EntidadeToAtividadeRecuperarDto(AtividadeEntidade atividadeEntidade){
        return AtividadeRecuperarDto.builder()
        .id(atividadeEntidade.getId())
        .usuario(UsuarioRestConverter.EntidadeToUsuarioRecuperarDto(atividadeEntidade.getUsuario()))
        .categoria(CategoriaRestConverter.EntidadeToCategoriaRecuperarDto(atividadeEntidade.getCategoria()))
        .titulo(atividadeEntidade.getTitulo())
        .createdAt(atividadeEntidade.getCreatedAt())
        .status(atividadeEntidade.getStatus())
        .arquivo(ArquivoRestConverter.EntidadeToArquivoRecuperarDto(atividadeEntidade.getArquivo()))
        .cargaHoraria(atividadeEntidade.getCargaHoraria())
        .comentario(atividadeEntidade.getComentario())
        .build();
    }

    public static AtividadeEntidade DtoToEntidadeJpa (AtividadeDto dto, UsuarioEntidade usuarioEntidade, CategoriaEntidade categoriaEntidade, ArquivoEntidade arquivoEntidade){
        return AtividadeEntidade.builder()
        .id(dto.getId())
        .usuario(usuarioEntidade)
        .categoria(categoriaEntidade)
        .titulo(dto.getTitulo())
        .createdAt(dto.getCreatedAt())
        .status(dto.getStatus())
        .arquivo(arquivoEntidade)
        .cargaHoraria(dto.getCargaHoraria())
        .comentario(dto.getComentario())
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
    //     .matricula(dto.getMatricula())
    //     .nome(dto.getNome())
    //     .email(dto.getEmail())
    //     .senha(dto.getSenha())
    //     .usuarioRepositorioJpa(usuarioRepositorioJpa)
    //     .cursoRepositorioJpa(cursoRepositorioJpa)
    //     .build();
    // }
}
