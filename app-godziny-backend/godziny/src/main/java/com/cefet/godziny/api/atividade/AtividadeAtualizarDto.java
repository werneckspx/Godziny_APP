package com.cefet.godziny.api.atividade;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import com.cefet.godziny.constantes.atividade.EnumStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Getter
@Setter
public class AtividadeAtualizarDto{

    private UUID id;

    @NotNull
    private Integer usuarioId;

    @NotNull
    @NotBlank
    private UUID categoriaId;

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @NotNull
    private EnumStatus status;

    @NotNull
    @NotBlank
    private UUID arquivoId;

    @NotNull
    @Positive
    private float cargaHoraria;

    @NotNull
    @NotBlank
    private String comentario;
}


