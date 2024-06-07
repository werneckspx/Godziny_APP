package com.cefet.godziny.infraestrutura.persistencia.categoria;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.cefet.godziny.domain.porta.categoria.ICategoriaRepositorio;
import com.cefet.godziny.infraestrutura.rest.categoria.CategoriaRestConverter;

@Component
public class CategoriaRepositorioJpa implements ICategoriaRepositorio{

    @Autowired
    private CategoriaRepositorioJpaSpring repositorio;

    public CategoriaRepositorioJpa(CategoriaRepositorioJpaSpring repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public CategoriaEntidade findById(UUID id) throws Exception {
        Optional<CategoriaEntidade> entidade = repositorio.findById(id);
        return CategoriaRestConverter.OptionalToCategoriaEntidade(entidade);
    }

    @Override
    public Page<CategoriaEntidade> listCategorias(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    @Override
    public UUID createCategoria(CategoriaEntidade newCategoria) {
        return repositorio.save(newCategoria).getId();
    }

    @Override
    public UUID updateCategoria(CategoriaEntidade newCategoria) throws Exception {
        Optional<CategoriaEntidade> categoria = this.repositorio.findById(newCategoria.getId());
        CategoriaRestConverter.OptionalToCategoriaEntidade(categoria);
        return repositorio.save(newCategoria).getId();
    }

    @Override
    public void deleteCategoria(UUID id) throws Exception {
        Optional<CategoriaEntidade> categoria = this.repositorio.findById(id);
        CategoriaRestConverter.OptionalToCategoriaEntidade(categoria);
        repositorio.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repositorio.deleteAll();
    }
}
