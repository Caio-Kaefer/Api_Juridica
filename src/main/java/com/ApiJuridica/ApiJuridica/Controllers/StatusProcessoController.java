package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Entities.StatusProcesso;
import com.ApiJuridica.ApiJuridica.Facades.StatusProcessoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status-Processo")
public class StatusProcessoController {

    private final StatusProcessoFacade statusProcessoFacade;

    @Autowired
    public StatusProcessoController(StatusProcessoFacade statusProcessoFacade) {
        this.statusProcessoFacade = statusProcessoFacade;
    }

    @GetMapping
    public ResponseEntity<List<StatusProcesso>> getAllStatus() {
        List<StatusProcesso> statusProcessos = statusProcessoFacade.getAllStatus();
        return ResponseEntity.ok(statusProcessos);
    }

    @PostMapping
    public ResponseEntity<StatusProcesso> createStatus(@RequestBody StatusProcesso statusProcesso) {
        StatusProcesso savedStatusProcesso = statusProcessoFacade.saveStatus(statusProcesso);
        return ResponseEntity.ok(savedStatusProcesso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusProcesso> getStatusById(@PathVariable Long id) {
        StatusProcesso statusProcesso = statusProcessoFacade.getStatusById(id);
        if (statusProcesso != null) {
            return ResponseEntity.ok(statusProcesso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        statusProcessoFacade.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}
