package com.cefet.godziny.domain.porta.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;

public interface IUsuarioRepositorio{

    UsuarioEntidade pesquisarPorId(Integer matricula) throws Exception;

    Page<UsuarioEntidade> listarUsuario(Pageable pageable);

    Page<UsuarioEntidade>listarUsuarioPorCurso(Pageable pageable, CursoEntidade curso);

    Integer criarUsuario(UsuarioEntidade newUsuario);

    Integer atualizarUsuario(UsuarioEntidade newUsuario) throws Exception;

    void deletarUsuario(Integer matricula) throws Exception;
}
