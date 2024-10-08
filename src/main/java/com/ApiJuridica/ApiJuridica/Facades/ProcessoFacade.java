package com.ApiJuridica.ApiJuridica.Facades;

import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Services.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProcessoFacade {

    private final ProcessoService processoService;

    @Autowired
    public ProcessoFacade(ProcessoService processoService) {
        this.processoService = processoService;
    }

    public Optional<Processo> getProcessoByNumero(String processo) {
        return processoService.findByNumeroProcesso(processo);
    }

}
