package com.cefet.godziny.api.atividade.arquivo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class dadosRespostaRecuperarDto {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String downloadURL;

    @NotNull
    @NotBlank
    private String tipo;

    @NotNull
    @NotBlank
    private long tamanhoArquivo;
}
