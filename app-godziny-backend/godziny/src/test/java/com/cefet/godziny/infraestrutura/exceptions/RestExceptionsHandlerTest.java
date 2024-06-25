package com.cefet.godziny.infraestrutura.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cefet.godziny.infraestrutura.exceptions.categoria.CategoriaNaoEncontradaException;
import com.cefet.godziny.infraestrutura.exceptions.categoria.CriarCategoriaInconpletaException;
import com.cefet.godziny.infraestrutura.exceptions.curso.CriarCursoIncompletoException;
import com.cefet.godziny.infraestrutura.exceptions.curso.CursoNaoEncontradoException;
import com.cefet.godziny.infraestrutura.exceptions.curso.RemoverCursoComCategoriasException;
import com.cefet.godziny.infraestrutura.exceptions.curso.RemoverCursoComUsuariosException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioEmailRepetidoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioIncompletoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.UsuarioNaoEncontradoException;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RestExceptionsHandlerTest {

    private RestExceptionsHandler restExceptionsHandler;

    @BeforeEach
    public void setup() {
        restExceptionsHandler = new RestExceptionsHandler();
    }

    @Test
    @DisplayName("Should create a CriarCursoIncompletoException successfully")
    public void testCriarCursoIncompletoException() throws Exception {
        CriarCursoIncompletoException exception = new CriarCursoIncompletoException("Curso incompleto");

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.criarCursoInclompletoException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCursoNaoEncontradoException() throws Exception {
        CursoNaoEncontradoException exception = new CursoNaoEncontradoException("Curso não encontrado");

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.cursoNaoEncontradoException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testRemoverCursoComUsuariosException() throws Exception {
        RemoverCursoComUsuariosException exception = new RemoverCursoComUsuariosException();

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.removerCursoComUsuarioException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void testCriarUsuarioIncompletoException() throws Exception {
        CriarUsuarioIncompletoException exception = new CriarUsuarioIncompletoException("Usuário incompleto");

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.criarUsuarioIncompletoException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCriarUsuarioEmailRepetidoException() throws Exception {
        CriarUsuarioEmailRepetidoException exception = new CriarUsuarioEmailRepetidoException();

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.criarUsuarioEmailRepetidoException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void testUsuarioNaoEncontradoException() throws Exception {
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException();

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.usuarioNaoEncontradoException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCategoriaNaoEncontradoException() throws Exception {
        CategoriaNaoEncontradaException exception = new CategoriaNaoEncontradaException();

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.categoriaNaoEncontradaException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCriarCategoriaInconpletaExceptionn() throws Exception {
        CriarCategoriaInconpletaException exception = new CriarCategoriaInconpletaException("Categora incompleta");

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.criarCategoriaInconpletaException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRemoverCursoComCategoriasException() throws Exception {
        RemoverCursoComCategoriasException exception = new RemoverCursoComCategoriasException();

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.removerCursoComCategoriaException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void testCampoRepetidoNoBancoException() throws Exception {
        CampoRepetidoNoBancoException exception = new CampoRepetidoNoBancoException("Campo repetido no banco de dados");

        ResponseEntity<RestDefaultErrorMessage> response = restExceptionsHandler.campoRepetidoNoBancoException(exception);

        assertThat(response.getBody()).isInstanceOf(RestDefaultErrorMessage.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }
}