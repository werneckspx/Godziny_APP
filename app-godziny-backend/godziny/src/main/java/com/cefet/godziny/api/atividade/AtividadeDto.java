package com.cefet.godziny.api.atividade;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import com.cefet.godziny.constantes.atividade.EnumStatus;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class AtividadeDto{

    private UUID id;

    @NotNull
    private String usuarioId;

    @NotNull
    private String categoriaId;

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @NotNull
    private EnumStatus status;

    @NotNull
    private String arquivoId;

    @Nullable
    private float cargaHoraria;

    @Nullable
    @NotBlank
    private String comentario;
}

