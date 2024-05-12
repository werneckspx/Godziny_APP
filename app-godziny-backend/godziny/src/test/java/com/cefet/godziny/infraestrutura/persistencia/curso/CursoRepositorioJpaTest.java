package com.cefet.godziny.infraestrutura.persistencia.curso;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.cefet.godziny.api.curso.CursoDto;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@DataJpaTest
@ActiveProfiles("test")
public class CursoRepositorioJpaTest {

    private static final String SIGLA = "ODONT_DIV";
    private static final Integer CARGA_HORARIA_COMPLEMENTAR = 615;
    private static final String NOME = "Odontologia";

    private CursoEntidade entidade;
    private CursoDto dto;

    @Autowired
    EntityManager entityManager;

    @AfterEach
    @Transactional
    void limparDados() {
        this.entidade = null;
        this.dto = null;

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @DisplayName("Search for a Curso and retun an existing successfully from DataBase")
    void testPesquisarPorId() {
        
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


     private CursoEntidade createCursoEntidade(){
        CursoEntidade curso = new CursoEntidade(SIGLA, NOME, CARGA_HORARIA_COMPLEMENTAR);
        entityManager.persist(curso);
        return curso;
    }

    private CursoDto createCursoDto(){
        CursoDto curso = new CursoDto(SIGLA, NOME, CARGA_HORARIA_COMPLEMENTAR);
        entityManager.persist(curso);
        return curso;
    }
}
