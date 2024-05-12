package com.cefet.godziny.infraestrutura.rest.curso;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.List;

@SpringBootTest
public class CursoControleTest {
    private static final String SIGLA = "ENG_ELET_BH";
    private static final Integer CARGA_HORARIA_COMPLEMENTAR = 500;
    private static final String NOME = "Engenharia Elétrica";

    private CursoEntidade entidade;
    private CursoDto dto;

    @InjectMocks
    CursoControle controler;

    @Mock
    CursoRepositorioJpa cursoRepositorioJpa;

    @Mock
    UsuarioRepositorioJpa usuarioRepositorioJpa;

    @BeforeEach
    @Transactional
    void inicializarDados() {
        MockitoAnnotations.openMocks(this);
        controler = new CursoControle(cursoRepositorioJpa, usuarioRepositorioJpa);
    };

    @AfterEach
    @Transactional
    void limparDados() {
        this.entidade = null;
        this.dto = null;

        cursoRepositorioJpa.deleteAll();
        usuarioRepositorioJpa.deleteAll();
    }
    
    @Test
    @DisplayName("Should search a Curso by SIGLA successfully")
    void testRecuperarCursoSuccess() throws Exception {
        this.entidade = createCursoEntidade();

        when(cursoRepositorioJpa.pesquisarPorId(Mockito.anyString())).thenReturn(entidade);
        ResponseEntity<CursoDto> response = controler.recuperarCurso(SIGLA);

        assertThat(response.getBody()).isInstanceOf(CursoDto.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Should list all Cursos successfully")
    void testListarCursosSuccess() {
        this.entidade = createCursoEntidade();
        Page<CursoEntidade> page = new PageImpl<>(List.of(entidade));
        Pageable pageable = PageRequest.of(0, 10);

        when(cursoRepositorioJpa.listarCursos(Mockito.any(Pageable.class))).thenReturn(page);
        ResponseEntity<Page<CursoDto>> response = controler.listarCursos(pageable);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).hasSizeGreaterThan(0); 
        assertThat(response.getBody().getSize()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Should create a new Curso successfully")
    void testCriarCursoSuccess() throws Exception {
        this.dto = createCursoDto();

        when(cursoRepositorioJpa.criarCurso(Mockito.any(CursoEntidade.class))).thenReturn(SIGLA);
        ResponseEntity<String> response = controler.criarCurso(dto);

        assertThat(response.getBody()).isInstanceOf(String.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Should update an existing Curso successfully")
    void testAtualizarCursoSuccess() throws Exception {
        this.entidade = createCursoEntidade();
        this.dto = createCursoDto();
        dto.setNome("Engnharia Elétrica Atualizada");

        when(cursoRepositorioJpa.atualizarCurso(Mockito.any(CursoEntidade.class))).thenReturn(SIGLA);
        when(cursoRepositorioJpa.pesquisarPorId(Mockito.anyString())).thenReturn(entidade);
        ResponseEntity<String> response = controler.atualizarCurso(dto);

        assertThat(response.getBody()).isInstanceOf(String.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Should delete a Curso successfully")
    void testRemoverCursoSuccess() throws Exception {
        this.entidade = createCursoEntidade();
        Page<UsuarioEntidade> pageUsers = new PageImpl<>(List.of());

        when(cursoRepositorioJpa.pesquisarPorId(Mockito.anyString())).thenReturn(entidade);
        when(usuarioRepositorioJpa.listarUsuarioPorCurso(Mockito.any(Pageable.class), Mockito.any(CursoEntidade.class))).thenReturn(pageUsers);
        ResponseEntity<Void> response = controler.removerCurso(SIGLA);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private CursoEntidade createCursoEntidade(){
        CursoEntidade curso = new CursoEntidade(SIGLA, NOME, CARGA_HORARIA_COMPLEMENTAR);
        return curso;
    }

    private CursoDto createCursoDto(){
        CursoDto curso = new CursoDto();
        curso.setSigla(SIGLA);
        curso.setNome(NOME);
        curso.setCarga_horaria_complementar(CARGA_HORARIA_COMPLEMENTAR);
        return curso;
    }
}