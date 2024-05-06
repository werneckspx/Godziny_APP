package com.cefet.godziny.api.usuario;

import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.constantes.usuario.EnumRecursos;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class UsuarioRecuperarDto{
    @NotNull
    @Positive
    private Integer matricula;

    @Nullable
    private CursoDto curso;
    
    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private EnumRecursos tipo;
}
