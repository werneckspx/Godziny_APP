package com.cefet.godziny.infraestrutura.exceptions.curso;

public class CursoNaoEncontradoException extends RuntimeException{

    public CursoNaoEncontradoException() {super("Curso não encontrado na base de dados");}

}
