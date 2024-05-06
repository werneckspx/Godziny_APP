package com.cefet.godziny.domain.porta.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;

public interface ICursoRepositorio {

    CursoEntidade pesquisarPorId(String id) throws Exception;

    Page<CursoEntidade> listarCursos(Pageable pageable);
    
    String criarCurso(CursoEntidade curso);

    String atualizarCurso(CursoEntidade newCurso) throws Exception;

    void deletarCurso(String id) throws Exception;
}