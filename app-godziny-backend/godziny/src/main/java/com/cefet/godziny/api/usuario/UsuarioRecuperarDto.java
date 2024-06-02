package com.cefet.godziny.api.usuario;

import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.constantes.usuario.EnumRecursos;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotNull
    private EnumRecursos tipo;
}
