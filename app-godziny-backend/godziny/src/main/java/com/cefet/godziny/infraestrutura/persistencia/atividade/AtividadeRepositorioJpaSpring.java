package com.cefet.godziny.infraestrutura.persistencia.atividade;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeRepositorioJpaSpring extends JpaRepository<AtividadeEntidade, UUID> {}