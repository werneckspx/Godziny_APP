package com.cefet.godziny.infraestrutura.rest.curso;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.api.curso.ICursoApi;
import com.cefet.godziny.domain.casouso.curso.CriarCursoCasoUso;
import com.cefet.godziny.domain.casouso.curso.RemoverCursoCasoUso;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoEntidade;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;

@RestController
public class CursoControle implements ICursoApi{

    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    public CursoControle(CursoRepositorioJpa cursoRepositorioJpa, UsuarioRepositorioJpa usuarioRepositorioJpa){
        this.cursoRepositorioJpa = cursoRepositorioJpa;
        this.usuarioRepositorioJpa = usuarioRepositorioJpa;
    }

    @Override
    public ResponseEntity<CursoDto> recuperarCurso(String sigla) throws Exception {
        var cursoDto = CursoRestConverter.EntidadeToCursoDto(cursoRepositorioJpa.pesquisarPorId(sigla));
        return ResponseEntity.status(HttpStatus.OK).body(cursoDto);
    }

    @Override
    public ResponseEntity<Page<CursoDto>> listarCursos(Pageable pageable) {
        Page<CursoDto> pageCursoDto = cursoRepositorioJpa.listarCursos(pageable).map(CursoRestConverter::EntidadeToCursoDto);
        return ResponseEntity.status(HttpStatus.OK).body(pageCursoDto);
    }

    @Override
    public ResponseEntity<String> criarCurso(@Valid CursoDto dto) throws Exception{
        CriarCursoCasoUso casoUso = CursoRestConverter.DtoToCriarCursoCasoUso(dto);
        casoUso.validarCriacao();
        var cursoEntidade = CursoRestConverter.DtoToEntidadeJpa(dto);
        String sigla = cursoRepositorioJpa.criarCurso(cursoEntidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(sigla);
    }

    @Override
    public ResponseEntity<String> atualizarCurso(@Valid CursoDto dto) throws Exception {
        CriarCursoCasoUso casoUso = CursoRestConverter.DtoToCriarCursoCasoUso(dto);
        casoUso.validarCriacao();
        var cursoEntidade = cursoRepositorioJpa.pesquisarPorId(dto.getSigla());
        return ResponseEntity.status(HttpStatus.OK).body(cursoRepositorioJpa.atualizarCurso(cursoEntidade));
    }

    @Override
    public ResponseEntity<Void> removerCurso(String sigla) throws Exception {
        RemoverCursoCasoUso casoUso = new RemoverCursoCasoUso(sigla, usuarioRepositorioJpa, cursoRepositorioJpa);
        casoUso.validarRemocao();
        cursoRepositorioJpa.deletarCurso(sigla);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
