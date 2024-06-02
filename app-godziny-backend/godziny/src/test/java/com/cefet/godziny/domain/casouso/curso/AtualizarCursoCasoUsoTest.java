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
public class AtualizarCursoCasoUsoTest {
    private CursoDto dto;

    @Mock
    CursoRepositorioJpa cursoRepositorioJpa;

    private AtualizarCursoCasoUso atualizarCursoCasoUso;

    @BeforeEach
    void inicializarDados() {
        atualizarCursoCasoUso = new AtualizarCursoCasoUso(cursoRepositorioJpa, "TESTE", "TESTE_TESTE", 100);
    };

    @AfterEach
    void limparDados() {
        this.dto = null;
        cursoRepositorioJpa.deleteAll();
    }
    
    @Test
    @DisplayName("Should valided a AtualizarCursoCasoUso successfully")
    void testeAtualizarCursoCasoUsoSuccess() throws Exception {
        this.dto = new CursoDto("TESTE", "TESTE_TESTE", 100);

        when(cursoRepositorioJpa.updateCurso(Mockito.anyString(), Mockito.any(CursoEntidade.class))).thenReturn("TESTE");
        atualizarCursoCasoUso.validarAtualizacao();
        String response = atualizarCursoCasoUso.AtualizarCurso("TESTE", dto);

        assertThat(response).isInstanceOf(String.class);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the SIGLA is too big")
    void testAtualizarCursoCasoUsoExceptionCase1() throws Exception{
        atualizarCursoCasoUso.setSigla("SIGLAS bigger than 20 letters are not allowed");

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            atualizarCursoCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A sigla do curso deve ter entre 3 e 20 caracteres");
    }

    @Test
    @DisplayName("Try to create a Curso and return an exception because the SIGLA is too short")
    void testeAtualizarCursoCasoUsoExceptionCase2() {
        atualizarCursoCasoUso.setSigla("S");
        
        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            atualizarCursoCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A sigla do curso deve ter entre 3 e 20 caracteres");
    }


    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the NOME too big")
    void testeAtualizarCursoCasoUsoExceptionCase3() throws Exception{
        atualizarCursoCasoUso.setNome("That NOME is too big! The Godziny's rules doesn't let this happen because it's not allowed and Who do want to put a long NOME like this?");

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            atualizarCursoCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O nome do curso deve ter entre 3 e 50 caracteres");
    }

    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the NOME is too short")
    void testeAtualizarCursoCasoUsoExceptionCase4() throws Exception{
        atualizarCursoCasoUso.setNome("N");

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            atualizarCursoCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O nome do curso deve ter entre 3 e 50 caracteres");
    }

    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the CARGA_HORARIA_COMPLEMENTAR is too big")
    void testeAtualizarCursoCasoUsoExceptionCase5() throws Exception{
        atualizarCursoCasoUso.setCargaHorariaComplementar(1000);

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            atualizarCursoCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A carga de horas complementares do curso deve estar entre 100 e 800");
    }

    @Test
    @DisplayName("Try to create a Curso and return an excepiton because the CARGA_HORARIA_COMPLEMENTAR is too short")
    void testeAtualizarCursoCasoUsoExceptionCase6() throws Exception{
        atualizarCursoCasoUso.setCargaHorariaComplementar(50);

        Exception thrown = assertThrows(CriarCursoIncompletoException.class, () -> {
            atualizarCursoCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A carga de horas complementares do curso deve estar entre 100 e 800");
    }

}

