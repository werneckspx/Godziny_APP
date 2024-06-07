package com.cefet.godziny.domain.porta.categoria;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaEntidade;

public interface ICategoriaRepositorio {
    CategoriaEntidade findById(UUID id) throws Exception;

    Page<CategoriaEntidade> listCategorias(Pageable pageable);
    
    UUID createCategoria(CategoriaEntidade categoria);

    UUID updateCategoria(CategoriaEntidade newCategoria) throws Exception;

    void deleteCategoria(UUID id) throws Exception;

    void deleteAll();
}
