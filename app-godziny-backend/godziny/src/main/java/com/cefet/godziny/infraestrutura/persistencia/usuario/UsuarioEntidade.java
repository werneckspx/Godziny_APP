package com.cefet.godziny.infraestrutura.persistencia.usuario;

import java.io.Serializable;
import com.cefet.godziny.constantes.usuario.EnumRecursos;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity(name = "usuario")
@Table(name = "tb_usuario")
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntidade implements Serializable{
    private static final long serialVersionUID  = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer matricula;

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private CursoEntidade curso;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    @Email(message = "O email fornecido para o usuário não é válido")
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumRecursos tipo;

}
