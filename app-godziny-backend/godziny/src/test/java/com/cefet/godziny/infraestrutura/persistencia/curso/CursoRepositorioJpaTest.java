package com.cefet.godziny.infraestrutura.persistencia.curso;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class CursoRepositorioJpaTest {

    private static final String SIGLA = "ODONT_DIV";
    private static final Integer CARGA_HORARIA_COMPLEMENTAR = 615;
    private static final String NOME = "Odontologia";

    private Optional<CursoEntidade> optional;
    private CursoEntidade entidade;

    @Mock
    CursoRepositorioJpaSpring cursoRepositorioJpaSpring;

    @InjectMocks
    CursoRepositorioJpa cursoRepositorio;

    @AfterEach
    @Transactional
    void limparDados() {
        this.optional = null;
        this.entidade = null;
    }

    @Test
    @DisplayName("Search for a Curso and retun an existing successfully from DataBase")
    void testPesquisarPorId() throws Exception {
        this.optional = createOptionalCurso();

        when(cursoRepositorioJpaSpring.findById(Mockito.anyString())).thenReturn(this.optional);
        CursoEntidade result = cursoRepositorio.pesquisarPorId(SIGLA);

        assertThat(result).isInstanceOf(CursoEntidade.class);
        assertThat(result).isNotNull();
    }


    @Test
    void testAtualizarCurso() {

    }

    @Test
    void testCriarCurso() {

    }

    @Test
    void testDeletarCurso() {

    }

    @Test
    void testDeleteAll() {

    }

    @Test
    void testListarCursos() {

    }

    private Optional<CursoEntidade> createOptionalCurso(){
        CursoEntidade curso = new CursoEntidade(SIGLA, NOME, CARGA_HORARIA_COMPLEMENTAR);
    
        Optional<CursoEntidade> cursoOptional = Optional.ofNullable(curso);
        return cursoOptional;
    }

    private CursoEntidade createCursoEntidade(){
        CursoEntidade curso = new CursoEntidade(SIGLA, NOME, CARGA_HORARIA_COMPLEMENTAR);
        return curso;
    }
}
