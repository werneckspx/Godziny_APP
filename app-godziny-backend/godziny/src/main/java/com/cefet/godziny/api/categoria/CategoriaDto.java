package com.cefet.godziny.api.categoria;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class CategoriaDto {

    private UUID id;
    
    @NotNull
    private UUID cursoId;
    
    @NotNull
    private String nome;

    @NotNull
    private int horas_maximas;

    @NotNull
    private float horas_multiplicador;

    @NotNull
    private String descricao;
}
