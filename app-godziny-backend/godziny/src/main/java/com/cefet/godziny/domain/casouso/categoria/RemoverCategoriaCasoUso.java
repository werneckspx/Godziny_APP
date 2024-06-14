package com.cefet.godziny.domain.casouso.categoria;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaRepositorioJpa;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
public class RemoverCategoriaCasoUso {
    @Autowired
    private final CategoriaRepositorioJpa categoriaRepositorioJpa;

    @NotNull(message = "O ID da categoria é obrigatório")
    private UUID id;

    public void validarRemocao() throws Exception {
        
    }

    public void removerCategoria() throws Exception {
        categoriaRepositorioJpa.deleteCategoria(this.id);
    }
}
