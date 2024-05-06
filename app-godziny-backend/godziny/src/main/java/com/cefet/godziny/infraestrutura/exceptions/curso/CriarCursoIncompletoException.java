package com.cefet.godziny.infraestrutura.exceptions.curso;

public class CriarCursoIncompletoException  extends RuntimeException{

    public CriarCursoIncompletoException() {super("Preenchimento errado dos campos do curso");}

    public CriarCursoIncompletoException(String mensagem) {super(mensagem);}
}
