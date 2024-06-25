package com.cefet.godziny.domain.porta.atividade;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.cefet.godziny.infraestrutura.persistencia.atividade.AtividadeEntidade;

public interface IAtividadeRepositorio {
    AtividadeEntidade findById(UUID id) throws Exception;

    Page<AtividadeEntidade> listAtividades(Pageable pageable);
    
    UUID createAtividade(AtividadeEntidade atividade);

    UUID updateAtividade(AtividadeEntidade newAtividade) throws Exception;

    void deleteAtividade(UUID id) throws Exception;

    void deleteAll();
}
