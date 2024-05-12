package com.cefet.godziny.infraestrutura.persistencia.curso;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.cefet.godziny.domain.porta.curso.ICursoRepositorio;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;

@Component
public class CursoRepositorioJpa implements ICursoRepositorio {

    @Autowired
    private final CursoRepositorioJpaSpring repositorio;

    public CursoRepositorioJpa(CursoRepositorioJpaSpring repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public CursoEntidade pesquisarPorId(String sigla) throws Exception {
        if(sigla == ""){
            return null;
        }
        Optional<CursoEntidade> curso = this.repositorio.findById(sigla);
        return CursoRestConverter.OptionalToCursoEntidade(curso);
    }

    @Override
    public Page<CursoEntidade> listarCursos(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    @Override
    public String criarCurso(CursoEntidade newCurso) {
        return repositorio.save(newCurso).getSigla();
    }

    @Override
    public String atualizarCurso(CursoEntidade newCurso) throws Exception {
        return repositorio.save(newCurso).getSigla();
    }

    @Override
    public void deletarCurso(String sigla) throws Exception {
        Optional<CursoEntidade> curso = this.repositorio.findById(sigla);
        CursoRestConverter.OptionalToCursoEntidade(curso);
        repositorio.deleteById(sigla);
    }

    @Override
    public void deleteAll(){
        repositorio.deleteAll();
    }
}
