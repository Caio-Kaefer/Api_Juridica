package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Facades.ProcessoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProcessoController {

    private final ProcessoFacade processoFacade;

    @Autowired
    public ProcessoController(ProcessoFacade processoFacade) {
        this.processoFacade = processoFacade;
    }

    @GetMapping("/processo/{numeroProcesso}")
    public ResponseEntity<Processo> getProcessoByNumero(@PathVariable String numeroProcesso) {
        Optional<Processo> processo = this.processoFacade.getProcessoByNumero(numeroProcesso);

        if (processo.isPresent()) {
            return ResponseEntity.ok(processo.get()); // Return the Processo object in JSON format
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if not present
        }
    }
}

