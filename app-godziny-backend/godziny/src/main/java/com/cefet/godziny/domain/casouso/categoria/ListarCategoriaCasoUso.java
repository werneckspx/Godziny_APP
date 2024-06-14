package com.cefet.godziny.domain.casouso.categoria;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.api.categoria.CategoriaRecuperarDto;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaEntidade;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.categoria.CategoriaRestConverter;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListarCategoriaCasoUso {
    @Autowired
    private final CategoriaRepositorioJpa categoriaRepositorioJpa;

    @NotNull(message = "O ID da categoria é obrigatório")
    private UUID id;

    public CategoriaRecuperarDto validarListagem() throws Exception {
        CategoriaEntidade entidade = categoriaRepositorioJpa.findById(this.id);
        return CategoriaRestConverter.EntidadeToCategoriaRecuperarDto(entidade, CursoRestConverter.EntidadeToCursoDto(entidade.getCurso()));
    }

    public Page<CategoriaRecuperarDto> listarCategorias(Pageable pageable) {
        Page<CategoriaRecuperarDto> pageCategoriaDto = categoriaRepositorioJpa.listCategorias(pageable).map(
            categoriaEntidade -> CategoriaRestConverter.EntidadeToCategoriaRecuperarDto(
                categoriaEntidade, 
                CursoRestConverter.EntidadeToCursoDto(categoriaEntidade.getCurso())
        ));
        return pageCategoriaDto;
    }
}
