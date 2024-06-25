package com.cefet.godziny.domain.casouso.categoria;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaEntidade;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;

@SpringBootTest
public class RemoverCategoriaCasoUsoTest {
    
    @Mock
    private CategoriaEntidade entidade;

    @Mock
    CategoriaRepositorioJpa categoriaRepositorioJpa;

    private RemoverCategoriaCasoUso removerCategoriaCasoUso;

    @BeforeEach
    void inicializarDados() {
        removerCategoriaCasoUso = new RemoverCategoriaCasoUso(categoriaRepositorioJpa, UUID.randomUUID());
    };

    @AfterEach
    void limparDados() {
        categoriaRepositorioJpa.deleteAll();
    }
    

    @Test
    @DisplayName("Should delete a Categoria successfully")
    void testRemoverCategoriaSuccess() throws Exception {
        this.entidade = createCategoriaEntidade();

        doNothing().when(categoriaRepositorioJpa).deleteCategoria(Mockito.any(UUID.class));
        removerCategoriaCasoUso.validarRemocao();
        removerCategoriaCasoUso.removerCategoria();

        verify(categoriaRepositorioJpa, times(1)).deleteCategoria(Mockito.any(UUID.class));
    }


     private CategoriaEntidade createCategoriaEntidade(){
        CategoriaEntidade entidade = new CategoriaEntidade(
            UUID.randomUUID(), 
            createCursoEntidade(),
            "categoria_TESTE",
            (float) 0.1, (float) 0.1,
            "descricao_TESTE"
        );
        return entidade;
    }

    private CursoEntidade createCursoEntidade(){
        CursoEntidade curso = new CursoEntidade(
            UUID.randomUUID(),
            "CURSO_ID_TESTE",
            "TESTE",
            100
        );
        return curso;
    }

}
