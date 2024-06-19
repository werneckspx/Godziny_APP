package com.cefet.godziny.domain.casouso.categoria;

import static org.mockito.Mockito.when;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.cefet.godziny.api.categoria.CategoriaDto;
import com.cefet.godziny.infraestrutura.exceptions.categoria.CriarCategoriaInconpletaException;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaEntidade;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CriarCategoriaCasoUsoTest {
    private CategoriaDto categoriaDto;
    private CategoriaEntidade categoriaEntidade;
    private CursoEntidade cursoEntidade;

    @Mock
    CursoRepositorioJpa cursoRepositorioJpa;

    @Mock
    CategoriaRepositorioJpa categoriaRepositorioJpa;

    private CriarCategoriaCasoUso criarCategoriaCasoUso;

    @BeforeEach
    void inicializarDados() {
        criarCategoriaCasoUso = new CriarCategoriaCasoUso(
            categoriaRepositorioJpa, cursoRepositorioJpa,
            "sigla_TESTE", "nome_TESTE",
            (float) 1.0, (float) 1.0,
            "descrição_TESTE"
        );
    };

    @AfterEach
    void limparDados() {
        this.categoriaDto = null;
        this.categoriaEntidade = null;
        this.cursoEntidade = null;
        categoriaRepositorioJpa.deleteAll();
        cursoRepositorioJpa.deleteAll();
    }
    
    @Test
    @DisplayName("Should valided an CriarCategoriaCasoUso successfully")
    void testeCriarCategoriaCasoUsoSuccess() throws Exception {
        this.categoriaDto = createCategoriaDto();
        this.categoriaEntidade = createCategoriaEntidade();
        this.cursoEntidade = createCursoEntidade();

        when(cursoRepositorioJpa.findBySigla(Mockito.anyString())).thenReturn(this.cursoEntidade);
        when(categoriaRepositorioJpa.createCategoria(Mockito.any(CategoriaEntidade.class))).thenReturn(UUID.randomUUID());
        criarCategoriaCasoUso.validarCriacao();
        UUID response = criarCategoriaCasoUso.createCategoria(this.categoriaDto, this.cursoEntidade);

        assertThat(response).isInstanceOf(UUID.class);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Try to create a Categoria and return an excepiton because the CURSOSIGLA is too big")
    void testCriarCategoriaCasoUsoExceptionCase1() throws Exception{
        criarCategoriaCasoUso.setCursoSigla("SIGLAS bigger than 20 letters are not allowed");

        Exception thrown = assertThrows(CriarCategoriaInconpletaException.class, () -> {
            criarCategoriaCasoUso.validarCriacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A sigla do curso na categoria deve ter entre 3 e 20 caracteres");
    }

    @Test
    @DisplayName("Try to create a Categoria and return an excepiton because the CURSOSIGLA is too short")
    void testCriarCategoriaCasoUsoExceptionCase2() throws Exception{
        criarCategoriaCasoUso.setCursoSigla("S");

        Exception thrown = assertThrows(CriarCategoriaInconpletaException.class, () -> {
            criarCategoriaCasoUso.validarCriacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A sigla do curso na categoria deve ter entre 3 e 20 caracteres");
    }


    private CategoriaDto createCategoriaDto(){
        return new CategoriaDto(
            UUID.randomUUID(), 
            "sigla_TESTE",
            "nome_TESTE",
            (float) 1.0, (float) 1.0,
            "descrição_TESTE"
        );
    }

    private CursoEntidade createCursoEntidade(){
        return new CursoEntidade(
            UUID.randomUUID(),
            "ODONT_DIV",
            "Odontologia",
            1
        );
    }

    private CategoriaEntidade createCategoriaEntidade(){
        return new CategoriaEntidade(
            UUID.randomUUID(),
            createCursoEntidade(), "Categoria_TESTE",
            (float) 1.0, (float) 1.0,
            "Descrição_TESTE");
    }

}

