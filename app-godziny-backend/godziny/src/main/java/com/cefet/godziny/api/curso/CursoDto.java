package com.cefet.godziny.api.curso;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class CursoDto{
    
    @NotNull
    private String sigla;

    @NotNull
    private String nome;

    @NotNull
    private Integer carga_horaria_complementar;
}
