package com.cefet.godziny.domain.porta.usuario;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;

public interface IUsuarioRepositorio{

    UsuarioEntidade findById(Integer matricula) throws Exception;

    UsuarioEntidade findByEmail(String email);

    Optional<UsuarioEntidade> findByEmailOptional(String email);

    Page<UsuarioEntidade> listUsuarios(Pageable pageable);

    Page<UsuarioEntidade>listUsuariosByCurso(Pageable pageable, CursoEntidade curso);

    Integer createUsuario(UsuarioEntidade newUsuario);

    Integer updateUsuario(UsuarioEntidade newUsuario) throws Exception;

    void deleteUsuario(Integer matricula) throws Exception;

    void deleteAll();
}
