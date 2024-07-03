package com.cefet.godziny.domain.casouso.usuario;

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
import com.cefet.godziny.constantes.usuario.EnumRecursos;
import com.cefet.godziny.infraestrutura.persistencia.atividade.AtividadeRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;

@SpringBootTest
public class RemoverUsuarioCasoUsoTest {
    
    @Mock
    private UsuarioEntidade entidade;

    @Mock
    UsuarioRepositorioJpa usuarioRepositorioJpa;

    @Mock
    private AtividadeRepositorioJpa atividadeRepositorioJpa;

    private RemoverUsuarioCasoUso removerUsuarioCasoUso;

    @BeforeEach
    void inicializarDados() {
        removerUsuarioCasoUso = new RemoverUsuarioCasoUso(99999, usuarioRepositorioJpa, atividadeRepositorioJpa);
    };

    @AfterEach
    void limparDados() {
        usuarioRepositorioJpa.deleteAll();
    }
    

    @Test
    @DisplayName("Should delete an Usuario successfully")
    void testRemoverUsuarioSuccess() throws Exception {
        this.entidade = createUsuarioEntidade();

        doNothing().when(usuarioRepositorioJpa).deleteUsuario(Mockito.anyInt());
        removerUsuarioCasoUso.validarRemocao();
        removerUsuarioCasoUso.removerUsuario();

        verify(usuarioRepositorioJpa, times(1)).deleteUsuario(Mockito.anyInt());
    }


     private UsuarioEntidade createUsuarioEntidade(){
        UsuarioEntidade entidade = new UsuarioEntidade(
            999999, 
            createCursoEntidade(),
            "TESTE",
            "teste@teste.com.br",
            "teste123",
            EnumRecursos.NORMAL
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
