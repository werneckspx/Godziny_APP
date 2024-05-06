package com.cefet.godziny.infraestrutura.persistencia.curso;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_curso")
@EqualsAndHashCode(of = "sigla")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoEntidade implements Serializable {
    private static final long serialVersionUID  = 1L;
    
    @Id
    private String sigla;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int carga_horaria_complementar;
}

