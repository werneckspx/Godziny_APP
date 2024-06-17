package com.cefet.godziny.api.curso;

import java.util.UUID;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class CursoDto{
    @Nullable
    private UUID id;
    
    @NotNull
    private String sigla;

    @NotNull
    private String nome;

    @NotNull
    private Integer carga_horaria_complementar;
}
