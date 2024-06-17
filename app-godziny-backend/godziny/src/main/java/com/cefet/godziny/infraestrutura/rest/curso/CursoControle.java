package com.cefet.godziny.infraestrutura.rest.curso;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.cefet.godziny.api.curso.CursoDto;
import com.cefet.godziny.api.curso.ICursoApi;
import com.cefet.godziny.domain.casouso.curso.CriarCursoCasoUso;
import com.cefet.godziny.domain.casouso.curso.ListarCursoCasoUso;
import com.cefet.godziny.domain.casouso.curso.RemoverCursoCasoUso;
import com.cefet.godziny.domain.casouso.curso.AtualizarCursoCasoUso;
import com.cefet.godziny.infraestrutura.persistencia.categoria.CategoriaRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.curso.CursoRepositorioJpa;
import com.cefet.godziny.infraestrutura.persistencia.usuario.UsuarioRepositorioJpa;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class CursoControle implements ICursoApi{

    @Autowired
    private final CursoRepositorioJpa cursoRepositorioJpa;

    @Autowired
    private final UsuarioRepositorioJpa usuarioRepositorioJpa;

    @Autowired
    private final CategoriaRepositorioJpa categoriaRepositorioJpa;

    @Override
    public ResponseEntity<CursoDto> getCurso(String sigla) throws Exception {
        ListarCursoCasoUso casoUso = new ListarCursoCasoUso(cursoRepositorioJpa, sigla);
        return ResponseEntity.status(HttpStatus.OK).body(casoUso.validarListagem());
    }

    @Override
    public ResponseEntity<Page<CursoDto>> listCursos(Pageable pageable) {
        ListarCursoCasoUso casoUso = new ListarCursoCasoUso(cursoRepositorioJpa, "");
        return  ResponseEntity.status(HttpStatus.OK).body(casoUso.listarCursos(pageable));
    }

    @Override
    public ResponseEntity<String> createCurso(@Valid CursoDto dto) throws Exception{
        CriarCursoCasoUso casoUso = CursoRestConverter.DtoToCriarCursoCasoUso(dto, cursoRepositorioJpa);
        casoUso.validarCriacao();
        return ResponseEntity.status(HttpStatus.CREATED).body(casoUso.createCurso(dto));
    }

    @Override
    public ResponseEntity<String> updateCurso(String cursoSigla, @Valid CursoDto dto) throws Exception {
        AtualizarCursoCasoUso casoUso = CursoRestConverter.DtoToUpdateCursoCasoUso(cursoSigla, dto, cursoRepositorioJpa);
        casoUso.validarAtualizacao();
        return ResponseEntity.status(HttpStatus.OK).body(casoUso.AtualizarCurso(cursoSigla, dto));
    }

    @Override
    public ResponseEntity<Void> removeCurso(String sigla) throws Exception {
        RemoverCursoCasoUso casoUso = new RemoverCursoCasoUso(cursoRepositorioJpa, usuarioRepositorioJpa, categoriaRepositorioJpa, sigla);
        casoUso.validarRemocao();
        casoUso.removerCurso();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
