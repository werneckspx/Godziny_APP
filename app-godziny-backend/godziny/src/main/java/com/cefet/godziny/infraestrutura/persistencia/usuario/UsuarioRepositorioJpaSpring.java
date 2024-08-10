package com.cefet.godziny.infraestrutura.persistencia.usuario;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface UsuarioRepositorioJpaSpring extends JpaRepository<UsuarioEntidade, Integer>, JpaSpecificationExecutor<UsuarioEntidade> {
    Optional<UsuarioEntidade> findByEmail(String email);

    Page<UsuarioEntidade> findByCurso(CursoEntidade curso, Pageable pageable);

    Page<UsuarioEntidade> findByNomeContaining(String nome, Pageable pageable);

    Page<UsuarioEntidade> findByMatricula(Integer matricula, Pageable pageable);

    Page<UsuarioEntidade> findByCursoAndNomeContaining(CursoEntidade curso, String nome, Pageable pageable);

    Page<UsuarioEntidade> findByCursoAndMatricula(CursoEntidade curso, Integer matricula, Pageable pageable);

    Page<UsuarioEntidade> findByNomeContainingAndMatricula(String nome, Integer matricula, Pageable pageable);
    
    Page<UsuarioEntidade> findByCursoAndNomeContainingAndMatricula(CursoEntidade curso, String nome, Integer matricula, Pageable pageable);
}

