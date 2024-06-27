package com.cefet.godziny.api.atividade;

import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/atividade")
public interface IAtividadeApi {
    @GetMapping("/{atividadeId}")
    ResponseEntity<AtividadeRecuperarDto> getAtividade(@PathVariable(value = "atividadeId") UUID id) throws Exception;

    @GetMapping("/list")
    ResponseEntity<Page<AtividadeRecuperarDto>> listAtividades(
        @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC)
        Pageable pageable
    );

    @PostMapping("")
    ResponseEntity<UUID> createAtividade(@RequestBody @Valid AtividadeDto dto) throws Exception;

    @PutMapping("/{atividadeId}")
    ResponseEntity<UUID> updateAtividade(@PathVariable(value = "atividadeId") UUID atividadeId, @RequestBody @Valid AtividadeDto dto) throws Exception;


    @DeleteMapping("/{atividadeId}")
    ResponseEntity<Void> removeAtividade(@PathVariable(value = "atividadeId") UUID atividadeId) throws Exception;
}
