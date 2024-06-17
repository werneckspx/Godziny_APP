package com.cefet.godziny.api.curso;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class CursoDto{
    private UUID id;
    
    @NotNull
    private String sigla;

    @NotNull
    private String nome;

    @NotNull
    private Integer carga_horaria_complementar;
}
