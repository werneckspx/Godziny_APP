package com.cefet.godziny.domain.casouso.atividade;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.constraints.NotNull;
import com.cefet.godziny.infraestrutura.persistencia.atividade.AtividadeRepositorioJpa;
import lombok.*;

@AllArgsConstructor
public class RemoverAtividadeCasoUso {
    @NotNull(message = "O ID da atividade é obrigatório")
    private UUID atividadeId;

    @Autowired
    private final AtividadeRepositorioJpa atividadeRepositorioJpa;

    public void validarRemocao() throws Exception {
        
    }

    public void removerAtividade() throws Exception {
        atividadeRepositorioJpa.deleteAtividade(this.atividadeId);
    }
}
