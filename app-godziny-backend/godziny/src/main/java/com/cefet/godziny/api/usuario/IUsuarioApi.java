package com.cefet.godziny.api.usuario;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
public interface IUsuarioApi {

    @GetMapping("/{matricula}")
    ResponseEntity<UsuarioRecuperarDto> recuperarUsuario(@PathVariable(value = "matricula") Integer matricula) throws Exception;

    @GetMapping("/lista")
    ResponseEntity<Page<UsuarioRecuperarDto>> listarUsuario(
        @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC)
        Pageable pageable
    ) throws Exception;

    @PostMapping("")
    ResponseEntity<Integer> criarUsuario(@RequestBody @Valid UsuarioDto dto) throws Exception;

    @PutMapping("")
    ResponseEntity<Integer> atualizarUsuario(@RequestBody @Valid UsuarioDto dto) throws Exception;

    @DeleteMapping("/{matricula}")
    ResponseEntity<Void> removerUsuario(@PathVariable(value = "matricula") Integer matricula) throws Exception;

}