package com.cefet.godziny.domain.casouso.curso;

import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.infraestrutura.exceptions.curso.CriarCursoIncompletoException;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CriarCursoCasoUsoTest {
    private CursoDto dto;

    @Mock
    CursoRepositorioJpa cursoRepositorioJpa;

    private CriarCursoCasoUso criarCursoCasoUso;

    @BeforeEach
    void inicializarDados() {
        criarCursoCasoUso = new CriarCursoCasoUso(cursoRepositorioJpa, "TESTE", "TESTE_TESTE", 100);
    };

    @AfterEach
    void limparDados() {
        this.dto = null;
        cursoRepositorioJpa.deleteAll();
    }
    
    @Test
    @DisplayName("Should valided a CriarCursoCasoUso successfully")
    void testCriarCursoCasoUsoSuccess() throws Exception {
        this.dto = new CursoDto("TESTE", "TESTE_TESTE", 100);

        when(cursoRepositorioJpa.createCurso(Mockito.any(CursoEntidade.class))).thenReturn("TESTE");
        criarCursoCasoUso.validarCriacao();
        String response = criarCursoCasoUso.createCurso(dto);

        assertThat(response).isInstanceOf(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the SIGLA is too big")
    void testCriarCursoCasoUsoExceptionCase1() throws Exception{
        criarCursoCasoUso.setSigla("SIGLAS bigger than 20 letters are not allowed");

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            criarCursoCasoUso.validarCriacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A sigla do curso deve ter entre 3 e 20 caracteres");
    }

    @Test
    @DisplayName("Try to create a Curso and return an exception because the SIGLA is too short")
    void testCriarCursoCasoUsoExceptionCase2() {
        criarCursoCasoUso.setSigla("S");
        
        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            criarCursoCasoUso.validarCriacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A sigla do curso deve ter entre 3 e 20 caracteres");
    }


    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the NOME too big")
    void testCriarCursoCasoUsoExceptionCase3() throws Exception{
        criarCursoCasoUso.setNome("That NOME is too big! The Godziny's rules doesn't let this happen because it's not allowed and Who do want to put a long NOME like this?");

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            criarCursoCasoUso.validarCriacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O nome do curso deve ter entre 3 e 50 caracteres");
    }

    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the NOME is too short")
    void testCriarCursoCasoUsoExceptionCase4() throws Exception{
        criarCursoCasoUso.setNome("N");

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            criarCursoCasoUso.validarCriacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O nome do curso deve ter entre 3 e 50 caracteres");
    }

    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the CARGA_HORARIA_COMPLEMENTAR is too big")
    void testCriarCursoCasoUsoExceptionCase5() throws Exception{
        criarCursoCasoUso.setCargaHorariaComplementar(1000);

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            criarCursoCasoUso.validarCriacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A carga de horas complementares do curso deve estar entre 100 e 800");
    }

    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the CARGA_HORARIA_COMPLEMENTAR is too short")
    void testCriarCursoCasoUsoExceptionCase6() throws Exception{
        criarCursoCasoUso.setCargaHorariaComplementar(50);

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            criarCursoCasoUso.validarCriacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A carga de horas complementares do curso deve estar entre 100 e 800");
    }


}

