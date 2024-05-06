package com.cefet.godziny.infraestrutura.persistencia.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;


@Repository
public interface UsuarioRepositorioJpaSpring extends JpaRepository<UsuarioEntidade, Integer> {
    Page<UsuarioEntidade> findByCurso(CursoEntidade curso, Pageable pageable);
}