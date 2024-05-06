package com.cefet.godziny.domain.casouso.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class RemoverUsuarioCasoUso {
    @NotNull(message = "A matrícula do usuario é obrigatório")
    private Integer matricula;


    public void validarRemocao() throws Exception {
        
    }
}
