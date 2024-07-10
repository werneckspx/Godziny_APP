package com.cefet.godziny.domain.casouso.atividade;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.constraints.NotNull;

import com.cefet.godziny.infraestrutura.persistencia.atividade.AtividadeEntidade;
import com.cefet.godziny.infraestrutura.persistencia.atividade.AtividadeRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.atividade.arquivo.ArquivoRepositorioJpa;

import lombok.*;

@AllArgsConstructor
public class RemoverAtividadeCasoUso {
    @NotNull(message = "O ID da atividade é obrigatório")
    private UUID atividadeId;

    @Autowired
    private final AtividadeRepositorioJpa atividadeRepositorioJpa;

    @Autowired
    private final ArquivoRepositorioJpa arquivoRepositorioJpa;


    public void validarRemocao() throws Exception {
        
    }

    public void removerAtividade() throws Exception {
        AtividadeEntidade atividadeEntidade = atividadeRepositorioJpa.findById(atividadeId);
        atividadeRepositorioJpa.deleteAtividade(this.atividadeId);
        arquivoRepositorioJpa.deleteArquivo(atividadeEntidade.getArquivo().getId());
    }
}
