package com.cefet.godziny.infraestrutura.rest.atividade;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import com.cefet.godziny.infraestrutura.exceptions.atividade.AtividadeNaoEncontradaException;
import com.cefet.godziny.infraestrutura.persistencia.atividade.AtividadeEntidade;
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

    // public static UsuarioRecuperarDto EntidadeToUsuarioRecuperarDto(UsuarioEntidade usuarioEntidade, CursoRecuperarDto cursoRecuperarDto){
    //     return UsuarioRecuperarDto.builder()
    //     .matricula(usuarioEntidade.getMatricula())
    //     .curso(cursoRecuperarDto)
    //     .nome(usuarioEntidade.getNome())
    //     .email(usuarioEntidade.getEmail())
    //     .senha(usuarioEntidade.getSenha())
    //     .tipo(usuarioEntidade.getTipo())
    //     .build();
    // }

    // public static UsuarioEntidade DtoToEntidadeJpa (UsuarioDto dto, CursoEntidade cursoEntidade){
    //     return UsuarioEntidade.builder()
    //     .matricula(dto.getMatricula())
    //     .curso(cursoEntidade)
    //     .nome(dto.getNome())
    //     .email(dto.getEmail())
    //     .senha(dto.getSenha())
    //     .tipo(dto.getTipo())
    //     .build();
    // }

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
