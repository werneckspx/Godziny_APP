package com.cefet.godziny.api.usuario;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class UsuarioFiltroDto{
    
    @Nullable
    private Integer matricula;
    
    @NotNull
    private String cursoSigla;
    
    @NotNull
    private String nome;
}
