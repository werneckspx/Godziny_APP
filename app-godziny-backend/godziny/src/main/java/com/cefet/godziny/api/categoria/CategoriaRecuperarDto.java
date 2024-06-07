package com.cefet.godziny.api.categoria;

import java.util.UUID;

import com.cefet.godziny.api.curso.CursoDto;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class CategoriaRecuperarDto {

    @NotNull
    private UUID id;
    
    @Nullable
    private CursoDto curso;
    
    @NotNull
    private String nome;

    @NotNull
    private int horas_maximas;

    @NotNull
    private float horas_multiplicador;

    @NotNull
    private String descricao;
}
