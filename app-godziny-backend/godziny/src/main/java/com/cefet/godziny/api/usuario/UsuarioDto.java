package com.cefet.godziny.api.usuario;

import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;
import com.cefet.godziny.constantes.usuario.EnumRecursos;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDto{
    
    private Integer matricula;
    
    @Nullable
    private String cursoId;
    
    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private EnumRecursos tipo;
}
