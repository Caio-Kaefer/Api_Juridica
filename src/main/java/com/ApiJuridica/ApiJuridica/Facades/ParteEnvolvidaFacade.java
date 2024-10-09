package com.ApiJuridica.ApiJuridica.Facades;

import com.ApiJuridica.ApiJuridica.Entities.Dtos.ParteEnvolvidaDto;
import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Entities.TipoParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Repositories.TIpoParteEnvolvidaRepository;
import com.ApiJuridica.ApiJuridica.Services.ParteEnvolvidaService;
import com.ApiJuridica.ApiJuridica.Services.TipoParteEnvolvidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParteEnvolvidaFacade {

    private final ParteEnvolvidaService parteService;
    private final TipoParteEnvolvidaService tipoService;

    @Autowired
    public ParteEnvolvidaFacade(ParteEnvolvidaService parteService, TipoParteEnvolvidaService tipoService ) {
        this.parteService = parteService;
        this.tipoService = tipoService;
    }

    public List<ParteEnvolvida> getAllPartesByProcessoId(Long processoId) {
        return parteService.getAllPartesByProcessoId(processoId);
    }

    public ParteEnvolvida saveParte(ParteEnvolvidaDto parteEnvolvidadto) {
        return parteService.saveParteEnvolvida(parteEnvolvidadto);
    }

    public ParteEnvolvida updateParte(ParteEnvolvidaDto parteEnvolvidaDto, Long id) {
        return parteService.updateParteEnvolvida(parteEnvolvidaDto, id);
    }

    public void deleteParte(Long id) {
        parteService.deleteParteEnvolvida(id);
    }

    public List<TipoParteEnvolvida> getAllTipos() {
        return tipoService.findAll();
    }

    public List<ParteEnvolvida> getPartesByTipo(Long tipoId) {
        List<TipoParteEnvolvida> tipos  = this.tipoService.findAll();
        boolean isValidTipoId = tipos.stream()
                .anyMatch(tipo -> tipo.getId().equals(tipoId));

        if (!isValidTipoId) {
            throw new IllegalArgumentException("Tipo ID " + tipoId + " não é válido.");
        }
        return parteService.getPartesByTipo(tipoId);
    }
}

