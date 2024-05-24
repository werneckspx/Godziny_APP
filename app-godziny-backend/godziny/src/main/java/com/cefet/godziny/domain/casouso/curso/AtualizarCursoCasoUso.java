package com.cefet.godziny.domain.casouso.curso;

import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.infraestrutura.exceptions.curso.CriarCursoIncompletoException;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class AtualizarCursoCasoUso {
     @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @NotNull(message = "A sigla do curso é obrigatória")
    private String sigla;

    @NotNull(message = "O nome do curso é obrigatório")
    private String nome;

    @NotNull(message = "A carga de horas complementares do curso é obrigatória")
    private int cargaHorariaComplementar;

    public void validarAtualizacao() throws Exception {
        if (sigla.length() < 3 || sigla.length() > 20) {
            throw new CriarCursoIncompletoException("A sigla do curso deve ter entre 3 e 20 caracteres");
        }
        if (nome.length() < 3 || nome.length() > 50) {
            throw new CriarCursoIncompletoException("O nome do curso deve ter entre 3 e 50 caracteres");
        }
        if (cargaHorariaComplementar < 100 || cargaHorariaComplementar > 800) {
            throw new CriarCursoIncompletoException("A carga de horas complementares do curso deve estar entre 100 e 800");
        }
    }

    public String AtualizarCurso(CursoDto dto) throws Exception{
        return cursoRepositorioJpa.updateCurso(CursoRestConverter.DtoToEntidadeJpa(dto));
    }
}
