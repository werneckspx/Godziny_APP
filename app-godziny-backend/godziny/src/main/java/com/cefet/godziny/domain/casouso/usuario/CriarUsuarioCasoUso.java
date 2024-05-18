package com.cefet.godziny.domain.casouso.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioEmailRepetidoException;
import com.cefet.godziny.infraestrutura.exceptions.usuario.CriarUsuarioIncompletoException;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CriarUsuarioCasoUso { 
    @NotNull(message = "O nome do usuário é obrigatório")
    private String nome;

    @NotNull(message = "O email do usuario é obrigatório")
    @Email(message = "O email fornecido para o usuário não é válido")
    private String email;

    @NotNull(message = "A senha do usuário é obrigatória")
    private String senha;

    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    public void validarCriacao() throws Exception {
        if (this.nome.length() < 3 || this.nome.length() > 100) {
            throw new CriarUsuarioIncompletoException("O nome do usuário deve ter entre 3 e 100 caracteres");
        }
        if (this.senha.length() < 6) {
            throw new CriarUsuarioIncompletoException("A senha deve ter no mínimo 6 caracteres");
        }
        if (!isValidEmailAddress(this.email)) {
            throw new CriarUsuarioIncompletoException("O email fornecido para o usuário não é válido");
        }
        if(usuarioRepositorioJpa.findByEmail(this.email) != null){
            throw new CriarUsuarioEmailRepetidoException();
        }
    }

    private boolean isValidEmailAddress(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
