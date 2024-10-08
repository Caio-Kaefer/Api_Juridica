package com.ApiJuridica.ApiJuridica.Facades;

import com.ApiJuridica.ApiJuridica.Services.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessoFacade {

    private final ProcessoService processoService;

    @Autowired
    public ProcessoFacade(ProcessoService processoService) {
        this.processoService = processoService;
    }

    public String GetName() {
        return processoService.GetName();
    }
}
