package com.cefet.godziny.infraestrutura.persistencia.atividade;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;

@Repository
public interface AtividadeRepositorioJpaSpring extends JpaRepository<AtividadeEntidade, UUID> {
    
    List<AtividadeEntidade> findByCategoria(CategoriaEntidade categoria);

    List<AtividadeEntidade> findByUsuario(UsuarioEntidade usuario);
    
}
