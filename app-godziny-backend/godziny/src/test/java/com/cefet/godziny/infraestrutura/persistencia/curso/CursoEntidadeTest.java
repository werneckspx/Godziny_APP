package com.cefet.godziny.infraestrutura.persistencia.curso;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;

@SpringBootTest
public class CursoEntidadeTest {

    @Test
    @DisplayName("Make sure if the objects are equals")
    void testEquals() {
        // Teste quando ambas as siglas são não nulas e iguais
        CursoEntidade curso1 = new CursoEntidade("SIGLA", "Nome do Curso", 100);
        CursoEntidade curso2 = new CursoEntidade("SIGLA", "Outro Nome", 150);
        assertTrue(curso1.equals(curso2));

        // Teste quando ambas as siglas são não nulas e diferentes
        CursoEntidade curso3 = new CursoEntidade("SIGLA", "Nome do Curso", 100);
        CursoEntidade curso4 = new CursoEntidade("OUTRA_SIGLA", "Outro Nome", 150);
        assertFalse(curso3.equals(curso4));

        // Teste quando a sigla do primeiro objeto é nula
        CursoEntidade curso5 = new CursoEntidade(null, "Nome do Curso", 100);
        CursoEntidade curso6 = new CursoEntidade("SIGLA", "Outro Nome", 150);
        assertFalse(curso5.equals(curso6));

        // Teste quando a sigla do segundo objeto é nula
        CursoEntidade curso7 = new CursoEntidade("SIGLA", "Nome do Curso", 100);
        CursoEntidade curso8 = new CursoEntidade(null, "Nome do Curso", 100);
        assertFalse(curso7.equals(curso8));

        // Teste quando ambas as siglas são nulas
        CursoEntidade curso9 = new CursoEntidade(null, "Nome do Curso", 100);
        CursoEntidade curso10 = new CursoEntidade(null, "Nome do Curso", 100);
        assertTrue(curso9.equals(curso10));

        // Teste quando o outro objeto é null
        CursoEntidade curso11 = new CursoEntidade("SIGLA", "Nome do Curso", 100);
        assertFalse(curso11.equals(null));

        // Teste quando os objetos são do mesmo tipo, mas siglas diferentes
        assertFalse(curso11.equals(new Object()));

        // Teste quando os objetos são o mesmo
        assertTrue(curso11.equals(curso11));

        // Teste quando ambas as siglas são nulas
        CursoEntidade curso14 = new CursoEntidade(null, "Nome do Curso", 100);
        CursoEntidade curso15 = new CursoEntidade(null, "Outro Nome", 150);
        assertTrue(curso14.equals(curso15));
    }

    @Test
    @DisplayName("Make sure if the objects have the same hashCode")
    void testHashCode() {
        // Teste quando ambas as siglas são não nulas e iguais
        CursoEntidade curso1 = new CursoEntidade("SIGLA", "Nome do Curso", 100);
        CursoEntidade curso2 = new CursoEntidade("SIGLA", "Outro Nome", 150);
        assertEquals(curso1.hashCode(), curso2.hashCode());

        // Teste quando ambas as siglas são não nulas e diferentes
        CursoEntidade curso3 = new CursoEntidade("SIGLA", "Nome do Curso", 100);
        CursoEntidade curso4 = new CursoEntidade("OUTRA_SIGLA", "Outro Nome", 150);
        assertNotEquals(curso3.hashCode(), curso4.hashCode());

        // Teste quando a sigla do primeiro objeto é nula
        CursoEntidade curso5 = new CursoEntidade(null, "Nome do Curso", 100);
        CursoEntidade curso6 = new CursoEntidade("SIGLA", "Outro Nome", 150);
        assertNotEquals(curso5.hashCode(), curso6.hashCode());

        // Teste quando a sigla do segundo objeto é nula
        CursoEntidade curso7 = new CursoEntidade("SIGLA", "Nome do Curso", 100);
        CursoEntidade curso8 = new CursoEntidade(null, "Nome do Curso", 100);
        assertNotEquals(curso7.hashCode(), curso8.hashCode());

        // Teste quando ambas as siglas são nulas
        CursoEntidade curso9 = new CursoEntidade(null, "Nome do Curso", 100);
        CursoEntidade curso10 = new CursoEntidade(null, "Outro Nome", 150);
        assertEquals(curso9.hashCode(), curso10.hashCode());
    }
}
