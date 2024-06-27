package com.cefet.godziny.infraestrutura.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cefet.godziny.infraestrutura.exceptions.atividade.AtividadeNaoEncontradaException;
import com.cefet.godziny.infraestrutura.exceptions.atividade.arquivo.ArquivoInvalidoException;
import com.cefet.godziny.infraestrutura.exceptions.atividade.arquivo.ArquivoNaoEncontradoException;
import com.cefet.godziny.infraestrutura.exceptions.categoria.CategoriaNaoEncontradaException;
import com.cefet.godziny.infraestrutura.exceptions.categoria.CriarCategoriaInconpletaException;
import com.cefet.godziny.infraestrutura.exceptions.curso.CriarCursoIncompletoException;
import com.cefet.godziny.infraestrutura.exceptions.curso.CursoNaoEncontradoException;
import com.cefet.godziny.infraestrutura.exceptions.curso.RemoverCursoComCategoriasException;
import com.cefet.godziny.infraestrutura.exceptions.curso.RemoverCursoComUsuariosException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioEmailRepetidoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioIncompletoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.UsuarioNaoEncontradoException;

@ControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CriarCursoIncompletoException.class)
    public ResponseEntity<RestDefaultErrorMessage> criarCursoInclompletoException(CriarCursoIncompletoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        error.getStatus();
        error.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(CursoNaoEncontradoException.class)
    public ResponseEntity<RestDefaultErrorMessage> cursoNaoEncontradoException(CursoNaoEncontradoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RemoverCursoComUsuariosException.class)
    public ResponseEntity<RestDefaultErrorMessage> removerCursoComUsuarioException(RemoverCursoComUsuariosException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CriarUsuarioIncompletoException.class)
    public ResponseEntity<RestDefaultErrorMessage> criarUsuarioIncompletoException(CriarUsuarioIncompletoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(CriarUsuarioEmailRepetidoException.class)
    public ResponseEntity<RestDefaultErrorMessage> criarUsuarioEmailRepetidoException(CriarUsuarioEmailRepetidoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<RestDefaultErrorMessage> usuarioNaoEncontradoException(UsuarioNaoEncontradoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    public ResponseEntity<RestDefaultErrorMessage> categoriaNaoEncontradaException(CategoriaNaoEncontradaException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CriarCategoriaInconpletaException.class)
    public ResponseEntity<RestDefaultErrorMessage> criarCategoriaInconpletaException(CriarCategoriaInconpletaException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RemoverCursoComCategoriasException.class)
    public ResponseEntity<RestDefaultErrorMessage> removerCursoComCategoriaException(RemoverCursoComCategoriasException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CampoRepetidoNoBancoException.class)
    public ResponseEntity<RestDefaultErrorMessage> campoRepetidoNoBancoException(CampoRepetidoNoBancoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(AtividadeNaoEncontradaException.class)
    public ResponseEntity<RestDefaultErrorMessage> atividadeNaoEncontradaException(AtividadeNaoEncontradaException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ArquivoNaoEncontradoException.class)
    public ResponseEntity<RestDefaultErrorMessage> arquivoNaoEncontradoException(ArquivoNaoEncontradoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ArquivoInvalidoException.class)
    public ResponseEntity<RestDefaultErrorMessage> arquivoInvalidoException(ArquivoInvalidoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
