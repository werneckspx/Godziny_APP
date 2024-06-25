package com.cefet.godziny.infraestrutura.persistencia.atividade;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.cefet.godziny.domain.porta.atividade.IAtividadeRepositorio;
import com.cefet.godziny.infraestrutura.rest.atividade.AtividadeRestConverter;

@Component
public class AtividadeRepositorioJpa implements IAtividadeRepositorio {
    @Autowired
    private AtividadeRepositorioJpaSpring repositorio;

    public AtividadeRepositorioJpa(AtividadeRepositorioJpaSpring repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public AtividadeEntidade findById(UUID id) throws Exception {
        Optional<AtividadeEntidade> atividade = repositorio.findById(id);
        return AtividadeRestConverter.OptionalToAtividadeEntidade(atividade);
    }

    @Override
    public Page<AtividadeEntidade> listAtividades(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    @Override
    public UUID createAtividade(AtividadeEntidade newAtividade) {
        return repositorio.save(newAtividade).getId();
    }

    @Override
    public UUID updateAtividade(AtividadeEntidade newAtividade) throws Exception {
        Optional<AtividadeEntidade> atividade = this.repositorio.findById(newAtividade.getId());
        AtividadeRestConverter.OptionalToAtividadeEntidade(atividade);
        return repositorio.save(newAtividade).getId();
    }

    @Override
    public void deleteAtividade(UUID id) throws Exception {
        Optional<AtividadeEntidade> atividade = this.repositorio.findById(id);
        AtividadeRestConverter.OptionalToAtividadeEntidade(atividade);
        repositorio.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repositorio.deleteAll();
    }
}
