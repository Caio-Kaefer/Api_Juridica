package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Facades.ProcessoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    private final ProcessoFacade processoFacade;

    @Autowired
    public ProcessoController(ProcessoFacade processoFacade) {
        this.processoFacade = processoFacade;
    }

    @GetMapping("/{numeroProcesso}")
    public ResponseEntity<Processo> getProcessoByNumero(@PathVariable String numeroProcesso) {
        Optional<Processo> processo = this.processoFacade.getProcessoByNumero(numeroProcesso);
        return processo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<Processo>> getAllProcessos() {
        List<Processo> processos = this.processoFacade.getAllProcessos();
        return ResponseEntity.ok(processos);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<Optional<List<Processo>>> getProcessoByStatus(@PathVariable Long statusId) {
        Optional<List<Processo>> processos = this.processoFacade.getProcessoByStatus(statusId);
        return ResponseEntity.ok(processos);
    }

    @PostMapping
    public ResponseEntity<Processo> createProcesso(@RequestBody Processo processo) {
        Processo savedProcesso = this.processoFacade.saveProcesso(processo);
        return ResponseEntity.status(201).body(savedProcesso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcesso(@PathVariable Long id) {
        processoFacade.deleteProcesso(id);
        return ResponseEntity.noContent().build();
    }
}
