package com.cefet.godziny.infraestrutura.persistencia.curso;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import com.cefet.godziny.infraestrutura.exceptions.curso.CursoNaoEncontradoException;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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


    @BeforeEach
    @Transactional
    void start() {
        MockitoAnnotations.openMocks(this);
        cursoRepositorio = new CursoRepositorioJpa(cursoRepositorioJpaSpring);
    };

    @AfterEach
    @Transactional
    void clean() {
        this.optional = null;
        this.entidade = null;
    }

    @Test
    @DisplayName("Search for a Curso and retun an existing successfully from DataBase")
    void testFindByIdSuccess() throws Exception {
        this.optional = createOptionalCurso();

        when(cursoRepositorioJpaSpring.findById(Mockito.anyString())).thenReturn(this.optional);
        CursoEntidade result = cursoRepositorio.findById(SIGLA);

        assertThat(result).isInstanceOf(CursoEntidade.class);
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("Search for a Curso without SIGLA and retun null")
    void testFindByIdSuccessWithoutSigla() throws Exception {
        CursoEntidade result = cursoRepositorio.findById("");

        assertThat(result).isNull();
    }

    @Test
    @DisplayName("Search for a Curso and retun an excepiton because the SIGLA doesn't exist")
    void testFindByIdCursoNaoEncontradoException() throws Exception {
        this.optional = Optional.empty();

        when(cursoRepositorioJpaSpring.findById(Mockito.anyString())).thenReturn(this.optional);
        Exception thrown = assertThrows(CursoNaoEncontradoException.class, () -> {
            cursoRepositorio.findById("Curso que não existe");
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("Curso não encontrado na base de dados");
    }
    
    @Test
    @DisplayName("Should list all Cursos successfully")
    void testListCursosSuccess() {
        this.entidade = createCursoEntidade();
        Page<CursoEntidade> page = new PageImpl<>(List.of(this.entidade));
        Pageable pageable = PageRequest.of(0, 10);

        when(cursoRepositorioJpaSpring.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        Page<CursoEntidade> result = cursoRepositorio.listCursos(pageable);

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Page.class);
        assertThat(result.getSize()).isNotNull();
        assertThat(result).hasSizeGreaterThan(0); 
    }

    @Test
    @DisplayName("Should create a Curso successfully")
    void testCreateCursoSuccess() {
        this.entidade = createCursoEntidade();

        when(cursoRepositorioJpaSpring.save(Mockito.any(CursoEntidade.class))).thenReturn(this.entidade);
        String result = cursoRepositorio.createCurso(entidade);

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(String.class);
        assertThat(result).isEqualTo(SIGLA);

    }

    @Test
    @DisplayName("Should update a Curso successfully")
    void testUpdateCursoSuccess() throws Exception {
        this.entidade = createCursoEntidade();
        this.optional = createOptionalCurso();

        when(cursoRepositorioJpaSpring.findById(Mockito.anyString())).thenReturn(this.optional);
        when(cursoRepositorioJpaSpring.save(Mockito.any(CursoEntidade.class))).thenReturn(this.entidade);
        String result = cursoRepositorio.updateCurso(entidade);

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(String.class);
        assertThat(result).isEqualTo(SIGLA);
    }

    @Test
    @DisplayName("Try to update a Curso andretun an excepiton because there isn't any Curso with that SIGLA")
    void testUpdateCursoCursoNaoEncontradoException() throws Exception {
        this.entidade = createCursoEntidade();
        this.optional = Optional.empty();

        when(cursoRepositorioJpaSpring.findById(Mockito.anyString())).thenReturn(this.optional);
        Exception thrown = assertThrows(CursoNaoEncontradoException.class, () -> {
            cursoRepositorio.updateCurso(entidade);
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("Curso não encontrado na base de dados");
    }

    @Test
    @DisplayName("Should delete a Curso successfully")
    void testDeleteCursoSuccess() throws Exception{
        this.optional = createOptionalCurso();

        when(cursoRepositorioJpaSpring.findById(Mockito.anyString())).thenReturn(this.optional);
        doNothing().when(cursoRepositorioJpaSpring).deleteById(Mockito.anyString());
        cursoRepositorio.deleteCurso(SIGLA);

        verify(cursoRepositorioJpaSpring, times(1)).deleteById(Mockito.anyString());
    }

    @Test
    @DisplayName("Try to delete a Curso and retun an excepiton because there isn't any Curso with that SIGLA")
    void testDeleteCursoCursoNaoEncontradoException() throws Exception{
        this.optional = Optional.empty();

        when(cursoRepositorioJpaSpring.findById(Mockito.anyString())).thenReturn(this.optional);
        Exception thrown = assertThrows(CursoNaoEncontradoException.class, () -> {
            cursoRepositorio.deleteCurso("Curso que não existe");
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("Curso não encontrado na base de dados");
    }

    @Test
    @DisplayName("Should delete allCurso successfully")
    void testDeleteAllSuccess() {
        doNothing().when(cursoRepositorioJpaSpring).deleteAll();
        cursoRepositorio.deleteAll();

        verify(cursoRepositorioJpaSpring, times(1)).deleteAll();
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
