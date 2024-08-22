package com.cefet.godziny.domain.casouso.curso;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.constantes.usuario.EnumRecursos;
import com.cefet.godziny.infraestrutura.exceptions.CampoRepetidoNoBancoException;
import com.cefet.godziny.infraestrutura.exceptions.curso.CriarCursoComUsuarioNormalException;
import com.cefet.godziny.infraestrutura.exceptions.curso.CriarCursoIncompletoException;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.curso.CursoRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@Setter
public class AtualizarCursoCasoUso {
    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    @NotNull(message = "O id do curso é obrigatório")
    private UUID id;

    @NotNull(message = "A sigla do curso é obrigatória")
    private String sigla;

    @NotNull(message = "A sigla antiiga do curso é obrigatória")
    private String siglaAntiga;

    @NotNull(message = "O nome do curso é obrigatório")
    private String nome;

    @NotNull(message = "A carga de horas complementares do curso é obrigatória")
    private int cargaHorariaComplementar;

    @NotNull(message = "A matricula do coordenador do curso é obrigatório")
    private Integer coordenador_matricula;

    public UsuarioEntidade validarAtualizacao() throws Exception {
        if (sigla.length() < 3 || sigla.length() > 20) {
            throw new CriarCursoIncompletoException("A sigla do curso deve ter entre 3 e 20 caracteres");
        }
        if (nome.length() < 3 || nome.length() > 50) {
            throw new CriarCursoIncompletoException("O nome do curso deve ter entre 3 e 50 caracteres");
        }
        if (cargaHorariaComplementar < 100 || cargaHorariaComplementar > 800) {
            throw new CriarCursoIncompletoException("A carga de horas complementares do curso deve estar entre 100 e 800");
        }
        CursoEntidade entidade = cursoRepositorioJpa.findBySigla(this.siglaAntiga);
        if(entidade != null && !entidade.getId().equals(this.id)){
            throw new CampoRepetidoNoBancoException("Já existe um Curso com essa sigla cadastrado na base de dados"); 
        }
        UsuarioEntidade coordenador = usuarioRepositorioJpa.findById(coordenador_matricula);
        if(coordenador.getTipo().equals(EnumRecursos.NORMAL)){
            throw new CriarCursoComUsuarioNormalException();
        }
        return coordenador;
    }

    public String AtualizarCurso(String cursoSigla, CursoDto dto, UsuarioEntidade coordenador) throws Exception{
        return cursoRepositorioJpa.updateCurso(cursoSigla, CursoRestConverter.DtoToEntidadeJpa(dto, coordenador));
    }
}
