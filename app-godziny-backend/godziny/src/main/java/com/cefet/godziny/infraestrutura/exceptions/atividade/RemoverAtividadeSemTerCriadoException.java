package com.cefet.godziny.infraestrutura.exceptions.atividade;

public class RemoverAtividadeSemTerCriadoException extends RuntimeException{

    public RemoverAtividadeSemTerCriadoException(){super("Somente o usuário que criou a atividade pode remove-la");}

}
