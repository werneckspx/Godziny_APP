package com.cefet.godziny.domain.atividade.arquivo;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import com.cefet.godziny.api.atividade.arquivo.ArquivoRecuperarDto;
import com.cefet.godziny.infraestrutura.persistencia.atividade.arquivo.ArquivoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.atividade.arquivo.ArquivoRepositorioJpa;
import com.cefet.godziny.infraestrutura.rest.atividade.arquivo.ArquivoRestConverter;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListarArquivoCasoUso {
    @Autowired
    private final ArquivoRepositorioJpa arquivoRepositorioJpa;

    @NotNull(message = "O ID do arquivo é obrigatório")
    private UUID id;

    public ArquivoRecuperarDto validarListagem() throws Exception {
        ArquivoEntidade entidade = arquivoRepositorioJpa.findById(this.id);
        createArquivoRecuperarDto(entidade);
        return ArquivoRestConverter.EntidadeToArquivoRecuperarDto(entidade);
    }

    private ArquivoRecuperarDto createArquivoRecuperarDto(ArquivoEntidade arquivoEntidade){
        ArquivoRecuperarDto dto = new  ArquivoRecuperarDto();
        dto.setId(arquivoEntidade.getId());
        dto.setNome(arquivoEntidade.getNome());
        dto.setTipo(arquivoEntidade.getTipo());
        dto.setDado(arquivoEntidade.getDado());
        dto.getId();
        dto.getNome();
        dto.getTipo();
        dto.getDado();
        return dto;
    }
}
