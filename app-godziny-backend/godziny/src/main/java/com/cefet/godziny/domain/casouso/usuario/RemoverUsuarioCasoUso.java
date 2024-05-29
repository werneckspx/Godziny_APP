package com.cefet.godziny.domain.casouso.usuario;

import org.springframework.beans.factory.annotation.Autowired;

import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
public class RemoverUsuarioCasoUso {
    @NotNull(message = "A matrícula do usuario é obrigatório")
    private Integer matricula;

    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    public void validarRemocao() throws Exception {
        
    }

    public void removerUsuario() throws Exception {
        usuarioRepositorioJpa.deleteUsuario(this.matricula);
    }
}
