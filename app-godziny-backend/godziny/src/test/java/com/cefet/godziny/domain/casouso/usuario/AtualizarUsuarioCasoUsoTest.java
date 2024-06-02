package com.cefet.godziny.domain.casouso.usuario;

import static org.mockito.Mockito.when;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.cefet.godziny.api.usuario.UsuarioDto;
import com.cefet.godziny.constantes.usuario.EnumRecursos;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioEmailRepetidoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioIncompletoException;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
    
@SpringBootTest
public class AtualizarUsuarioCasoUsoTest {
    private UsuarioDto usuarioDto;
    private CursoEntidade cursoEntidade;

    @Mock
    CursoRepositorioJpa cursoRepositorioJpa;

    @Mock
    UsuarioRepositorioJpa usuarioRepositorioJpa;

    private AtualizarUsuarioCasoUso atualizarUsuarioCasoUso;

    @BeforeEach
    void inicializarDados() {
        atualizarUsuarioCasoUso = new AtualizarUsuarioCasoUso("TESTE", "teste@teste.com.br", "teste123",  usuarioRepositorioJpa, cursoRepositorioJpa);
    };

    @AfterEach
    void limparDados() {
        this.usuarioDto = null;
        this.cursoEntidade = null;
        usuarioRepositorioJpa.deleteAll();
        cursoRepositorioJpa.deleteAll();
    }
    
    @Test
    @DisplayName("Should valided an AtualizarUsuarioCasoUso successfully")
    void testeAtualizarUsuarioCasoUsoSuccess() throws Exception {
        this.usuarioDto = createUsuarioDto();
        this.cursoEntidade = createCursoEntidade();

        when(cursoRepositorioJpa.findBySigla(Mockito.anyString())).thenReturn(cursoEntidade);
        when(usuarioRepositorioJpa.updateUsuario(Mockito.any(UsuarioEntidade.class))).thenReturn(99999);
        atualizarUsuarioCasoUso.validarAtualizacao();
        Integer response = atualizarUsuarioCasoUso.AtualizarUsuario(usuarioDto);

        assertThat(response).isInstanceOf(Integer.class);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Try to update an Usuario and return an excepiton because the NOME is too big")
    void testAtualizarUsuarioCasoUsoExceptionCase1() throws Exception{
        atualizarUsuarioCasoUso.setNome("NOME's bigger than 100 letters are not allowed. It's really difficuty someone has a name's length as one hundred letters. But the system stiil has that rule.");

        Exception thrown = assertThrows( CriarUsuarioIncompletoException.class, () -> {
            atualizarUsuarioCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O nome do usuário deve ter entre 3 e 100 caracteres");
    }

    @Test
    @DisplayName("Try to update an Usuario and return an exception because the NOME is too short")
    void testeAtualizarUsuarioCasoUsoExceptionCase2() {
        atualizarUsuarioCasoUso.setNome("N");
        
        Exception thrown = assertThrows(CriarUsuarioIncompletoException.class, () -> {
            atualizarUsuarioCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O nome do usuário deve ter entre 3 e 100 caracteres");
    }  

    @Test
    @DisplayName("Try to update an Usuario and return an excepiton because the SENHA is too short")
    void testeAtualizarUsuarioCasoUsoExceptionCase3() throws Exception{
        atualizarUsuarioCasoUso.setSenha("123");

        Exception thrown = assertThrows(CriarUsuarioIncompletoException.class, () -> {
            atualizarUsuarioCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("A senha deve ter no mínimo 6 caracteres");
    }

    @Test
    @DisplayName("Try to update an Usuario and return an exception because the EMAIL is NULL")
    void testeAtualizarUsuarioCasoUsoExceptionCase4() {
        atualizarUsuarioCasoUso.setEmail(null);
        
        Exception thrown = assertThrows(CriarUsuarioIncompletoException.class, () -> {
            atualizarUsuarioCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O email fornecido para o usuário não é válido");
    }  

    @Test
    @DisplayName("Try to update an Usuario and return an exception because the EMAIL isn't right")
    void testeAtualizarUsuarioCasoUsoExceptionCase5() {
        atualizarUsuarioCasoUso.setEmail("testehotmail.com");
        
        Exception thrown = assertThrows(CriarUsuarioIncompletoException.class, () -> {
            atualizarUsuarioCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O email fornecido para o usuário não é válido");
    }

    @Test
    @DisplayName("Try to update an Usuario and return an exception because the EMAIL is already in the database")
    void testeAtualizarUsuarioCasoUsoExceptionCase6() {
        UsuarioEntidade usuarioEntidade = new UsuarioEntidade(
            999999, 
            createCursoEntidade(),
            "TESTE",
            "teste@teste.com.br",
            "teste123",
            EnumRecursos.NORMAL
        );

        when(usuarioRepositorioJpa.findByEmail(Mockito.anyString())).thenReturn(usuarioEntidade);
        
        Exception thrown = assertThrows(CriarUsuarioEmailRepetidoException.class, () -> {
            atualizarUsuarioCasoUso.validarAtualizacao();
        });
        
        assertThat(thrown).isNotNull();
        assertThat(thrown.getMessage()).isEqualTo("O email fornecido já está cadastrado no sistema");
    }

    private UsuarioDto createUsuarioDto(){
        UsuarioDto dto = new UsuarioDto(
            999999, 
            "CURSO_ID_TESTE",
            "TESTE",
            "teste@teste.com.br",
            "teste123",
            EnumRecursos.NORMAL
        );
        return dto;
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