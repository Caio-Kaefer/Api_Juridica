package com.ApiJuridica.ApiJuridica.Facades;

import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Services.ParteEnvolvidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParteEnvolvidaFacade {

    private final ParteEnvolvidaService parteService;

    @Autowired
    public ParteEnvolvidaFacade(ParteEnvolvidaService parteService) {
        this.parteService = parteService;
    }

    // Facade method to get all Partes
    public List<ParteEnvolvida> getAllPartesByProcessoId(Long processoId) {
        return parteService.getAllPartesByProcessoId(processoId);
    }
}
