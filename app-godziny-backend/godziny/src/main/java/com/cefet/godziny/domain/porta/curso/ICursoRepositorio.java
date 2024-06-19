package com.cefet.godziny.domain.porta.curso;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;

public interface ICursoRepositorio {

    CursoEntidade findBySigla(String sigla) throws Exception;

    Optional<CursoEntidade>findBySiglaOptional(String sigla);

    Page<CursoEntidade> listCursos(Pageable pageable);
    
    String createCurso(CursoEntidade curso);

    String updateCurso(String cursoSigla, CursoEntidade newCurso) throws Exception;

    void deleteCurso(String id) throws Exception;

    void deleteAll();
}