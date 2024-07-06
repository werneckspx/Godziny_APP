package com.cefet.godziny.domain.casouso.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.godziny.api.usuario.UsuarioDto;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioEmailRepetidoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioIncompletoException;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioEntidade;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.usuario.UsuarioRestConverter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
    
@Builder
@AllArgsConstructor
@Setter
public class AtualizarUsuarioCasoUso {
    @NotNull(message = "A matricula do usuário é obrigatória")
    private Integer matricula;

    @NotNull(message = "O nome do usuário é obrigatório")
    private String nome;

    @NotNull(message = "O email do usuario é obrigatório")
    @Email(message = "O email fornecido para o usuário não é válido")
    private String email;

    @NotNull(message = "A senha do usuário é obrigatória")
    private String senha;

    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    public void validarAtualizacao() throws Exception {
        if (this.nome.length() < 3 || this.nome.length() > 100) {
            throw new CriarUsuarioIncompletoException("O nome do usuário deve ter entre 3 e 100 caracteres");
        }
        if (this.senha.length() < 6) {
            throw new CriarUsuarioIncompletoException("A senha deve ter no mínimo 6 caracteres");
        }
        if (!isValidEmailAddress(this.email)) {
            throw new CriarUsuarioIncompletoException("O email fornecido para o usuário não é válido");
        }
        UsuarioEntidade entidade = usuarioRepositorioJpa.findByEmail(this.email);
        if(entidade != null && !entidade.getMatricula().equals(this.matricula)){
            throw new CriarUsuarioEmailRepetidoException();
        }
    }

    private boolean isValidEmailAddress(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public Integer AtualizarUsuario(UsuarioDto dto) throws Exception{
        UsuarioEntidade usuarioEntidade = UsuarioRestConverter.DtoToEntidadeJpa(dto, cursoRepositorioJpa.findBySigla(dto.getCursoSigla()));
        return usuarioRepositorioJpa.updateUsuario(usuarioEntidade);
    }
}

