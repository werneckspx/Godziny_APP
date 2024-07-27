package com.cefet.godziny.api.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class UsuarioFiltroDto{
    
    @NotNull
    private Integer matricula;
    
    @NotNull
    private String cursoSigla;
    
    @NotNull
    private String nome;
}
