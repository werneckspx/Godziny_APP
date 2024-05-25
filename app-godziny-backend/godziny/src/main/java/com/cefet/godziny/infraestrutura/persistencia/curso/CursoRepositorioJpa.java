package com.cefet.godziny.infraestrutura.persistencia.curso;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.godziny.domain.porta.curso.ICursoRepositorio;
import com.cefet.godziny.infraestrutura.exceptions.curso.CursoNaoEncontradoException;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;

@Component
public class CursoRepositorioJpa implements ICursoRepositorio {

    @Autowired
    private final CursoRepositorioJpaSpring repositorio;

    public CursoRepositorioJpa(CursoRepositorioJpaSpring repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public CursoEntidade findById(String sigla) throws Exception {
        if(sigla == ""){
            return null;
        }
        Optional<CursoEntidade> curso = this.repositorio.findById(sigla);
        return CursoRestConverter.OptionalToCursoEntidade(curso);
    }

    @Override
    public Page<CursoEntidade> listCursos(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    @Override
    public String createCurso(CursoEntidade newCurso) {
        return repositorio.save(newCurso).getSigla();
    }

    @Override
    @Transactional
    public String updateCurso(String cursoSigla, CursoEntidade newCurso) throws Exception {
        Optional<CursoEntidade> curso;
        if(!cursoSigla.equals(newCurso.getSigla())){
            curso = this.repositorio.findById(newCurso.getSigla());
            if(curso.isPresent()){
                throw new CursoNaoEncontradoException("Já existe um Curso com essa sigla cadastrado na base de dados");
            }
        }
        curso = this.repositorio.findById(cursoSigla);
        CursoRestConverter.OptionalToCursoEntidade(curso);
        repositorio.updateCursoById(cursoSigla, newCurso.getSigla(), newCurso.getCarga_horaria_complementar(), newCurso.getNome());
        return newCurso.getSigla();
    }

    @Override
    public void deleteCurso(String sigla) throws Exception {
        Optional<CursoEntidade> curso = this.repositorio.findById(sigla);
        CursoRestConverter.OptionalToCursoEntidade(curso);
        repositorio.deleteById(sigla);
    }

    @Override
    public void deleteAll(){
        repositorio.deleteAll();
    }
}
