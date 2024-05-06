package com.cefet.godziny.infraestrutura.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cefet.godziny.infraestrutura.exceptions.curso.CriarCursoIncompletoException;
import com.cefet.godziny.infraestrutura.exceptions.curso.CursoNaoEncontradoException;
import com.cefet.godziny.infraestrutura.exceptions.curso.RemoverCursoComUsuariosException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioEmailRepetidoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioIncompletoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.UsuarioNaoEncontradoException;

@ControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CriarCursoIncompletoException.class)
    public ResponseEntity<RestDefaultErrorMessage> criarCursoInclompletoException(CriarCursoIncompletoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(CursoNaoEncontradoException.class)
    public ResponseEntity<RestDefaultErrorMessage> cursoNaoEncontradoException(CursoNaoEncontradoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(RemoverCursoComUsuariosException.class)
    public ResponseEntity<RestDefaultErrorMessage> removerCursoComUsuarioException(RemoverCursoComUsuariosException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(CriarUsuarioIncompletoException.class)
    public ResponseEntity<RestDefaultErrorMessage> criarUsuarioIncompletoException(CriarUsuarioIncompletoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(CriarUsuarioEmailRepetidoException.class)
    public ResponseEntity<RestDefaultErrorMessage> criarUsuarioEmailRepetidoException(CriarUsuarioEmailRepetidoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<RestDefaultErrorMessage> usuarioNaoEncontradoException(UsuarioNaoEncontradoException exception) {
        RestDefaultErrorMessage error = new RestDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
