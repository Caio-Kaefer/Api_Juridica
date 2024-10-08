package com.ApiJuridica.ApiJuridica.Controllers;

import com.ApiJuridica.ApiJuridica.Facades.ProcessoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessoController {

    private final ProcessoFacade processoFacade;

    @Autowired
    public ProcessoController(ProcessoFacade processoFacade) {
        this.processoFacade = processoFacade;
    }

    @GetMapping("/")
    public String hello() {
        return processoFacade.GetName();
    }
}
