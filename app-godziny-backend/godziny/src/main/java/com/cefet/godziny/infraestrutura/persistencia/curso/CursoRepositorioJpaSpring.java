package com.cefet.godziny.infraestrutura.persistencia.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepositorioJpaSpring extends JpaRepository<CursoEntidade, String> {

    void deleteById(String id);

}
