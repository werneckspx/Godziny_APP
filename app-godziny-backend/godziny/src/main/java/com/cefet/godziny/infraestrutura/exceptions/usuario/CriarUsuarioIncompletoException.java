package com.cefet.godziny.infraestrutura.exceptions.usuario;

public class CriarUsuarioIncompletoException extends RuntimeException{

    public CriarUsuarioIncompletoException() {super("Preenchimento errado dos campos do usuário");}

    public CriarUsuarioIncompletoException(String mensagem) {super(mensagem);}
}
