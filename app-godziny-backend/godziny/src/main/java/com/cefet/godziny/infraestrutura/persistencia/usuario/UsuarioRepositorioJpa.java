package com.cefet.godziny.infraestrutura.persistencia.usuario;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.cefet.godziny.domain.porta.usuario.IUsuarioRepositorio;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.rest.usuario.UsuarioRestConverter;

@Component
public class UsuarioRepositorioJpa implements IUsuarioRepositorio {

    @Autowired
    private UsuarioRepositorioJpaSpring repositorio;

    public UsuarioRepositorioJpa(UsuarioRepositorioJpaSpring repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public UsuarioEntidade pesquisarPorId(Integer matricula) throws Exception{
        Optional<UsuarioEntidade> entidade = repositorio.findById(matricula);
        return UsuarioRestConverter.OptionalToUsuarioEntidade(entidade);
    }

    @Override
    public UsuarioEntidade pesquisarPorEmail(String email) throws Exception {
        Optional<UsuarioEntidade> entidade = repositorio.findByEmail(email);
        if(!entidade.isPresent()){
            return null;
        }
        var usuarioEntidade = new UsuarioEntidade();
        BeanUtils.copyProperties(entidade.get(), usuarioEntidade);
        return usuarioEntidade;
    }

    @Override
    public Page<UsuarioEntidade> listarUsuario(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    @Override
    public Page<UsuarioEntidade> listarUsuarioPorCurso(Pageable pageable, CursoEntidade curso) {
        return repositorio.findByCurso(curso, pageable);
    }

    @Override
    public Integer criarUsuario(UsuarioEntidade newUsuario) {
        return repositorio.save(newUsuario).getMatricula();
    }

    @Override
    public Integer atualizarUsuario(UsuarioEntidade newUsuario) throws Exception {
        return repositorio.save(newUsuario).getMatricula();
    }
    
    @Override
    public void deletarUsuario(Integer matricula) throws Exception {
        Optional<UsuarioEntidade> usuario = this.repositorio.findById(matricula);
        UsuarioRestConverter.OptionalToUsuarioEntidade(usuario);
        repositorio.deleteById(matricula);
    }

    @Override
    public void deleteAll(){
        repositorio.deleteAll();
    }
}
