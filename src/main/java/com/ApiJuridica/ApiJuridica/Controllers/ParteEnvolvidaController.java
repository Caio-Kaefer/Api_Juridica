package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Facades.ParteEnvolvidaFacade;
import com.ApiJuridica.ApiJuridica.Facades.ParteEnvolvidaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParteEnvolvidaController {

    private final ParteEnvolvidaFacade parteFacade;

    @Autowired
    public ParteEnvolvidaController(ParteEnvolvidaFacade parteFacade) {
        this.parteFacade = parteFacade;
    }

    @GetMapping("/processo/{processoId}/partes")
    public ResponseEntity<List<ParteEnvolvida>> getAllPartesByProcessoId(@PathVariable Long processoId) {
        List<ParteEnvolvida> partes = parteFacade.getAllPartesByProcessoId(processoId);
        if (partes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(partes);
    }
}
