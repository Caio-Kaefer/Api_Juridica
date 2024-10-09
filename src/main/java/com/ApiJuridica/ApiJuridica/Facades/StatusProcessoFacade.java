package com.ApiJuridica.ApiJuridica.Facades;

import com.ApiJuridica.ApiJuridica.Entities.StatusProcesso;
import com.ApiJuridica.ApiJuridica.Services.StatusProcessoService;
import com.ApiJuridica.ApiJuridica.Services.StatusProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatusProcessoFacade {

    private final StatusProcessoService statusProcessoService;

    @Autowired
    public StatusProcessoFacade(StatusProcessoService statusProcessoService) {
        this.statusProcessoService = statusProcessoService;
    }

    public List<StatusProcesso> getAllStatus() {
        return statusProcessoService.getAllStatus();
    }

    public StatusProcesso saveStatus(StatusProcesso statusProcesso) {
        return statusProcessoService.saveStatus(statusProcesso);
    }

    public StatusProcesso getStatusById(Long id) {
        return statusProcessoService.getStatusById(id);
    }

    public void deleteStatus(Long id) {
        statusProcessoService.deleteStatus(id);
    }
}
