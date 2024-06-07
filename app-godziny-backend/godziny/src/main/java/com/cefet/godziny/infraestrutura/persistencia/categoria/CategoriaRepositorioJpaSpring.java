package com.cefet.godziny.infraestrutura.persistencia.categoria;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorioJpaSpring extends JpaRepository<CategoriaEntidade, UUID> {
    
}
