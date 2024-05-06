package com.cefet.godziny.api.curso;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/curso")
public interface ICursoApi {

    @GetMapping("/{cursoId}")
    ResponseEntity<CursoDto> recuperarCurso(@PathVariable(value = "cursoId") String sigla) throws Exception;

    @GetMapping("/lista")
    ResponseEntity<Page<CursoDto>> listarCurso(
        @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC)
        Pageable pageable
    );

    @PostMapping("")
    ResponseEntity<String> criarCurso(@RequestBody @Valid CursoDto dto) throws Exception;

    @PutMapping("")
    ResponseEntity<String> atualizarCurso(@RequestBody @Valid CursoDto dto) throws Exception;


    @DeleteMapping("/{cursoId}")
    ResponseEntity<Void> removerCurso(@PathVariable(value = "cursoId") String sigla) throws Exception;
}