package com.ApiJuridica.ApiJuridica.Facades;

import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Entities.StatusProcesso;
import com.ApiJuridica.ApiJuridica.Services.ProcessoService;
import com.ApiJuridica.ApiJuridica.Services.StatusProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessoFacade {

    private final ProcessoService processoService;
    private final StatusProcessoService statusProcessoService;

    @Autowired
    public ProcessoFacade(ProcessoService processoService, StatusProcessoService statusProcessoService) {
        this.processoService = processoService;
        this.statusProcessoService = statusProcessoService;
    }

    public Optional<Processo> getProcessoByNumero(String processo) {
        return processoService.findByNumeroProcesso(processo);
    }

    public List<Processo> getAllProcessos() {
        return processoService.findAll();
    }

    public Optional <List<Processo>> getProcessoByStatus(Long statusId) {
        List<StatusProcesso> statusValidos = statusProcessoService.getAllStatus();
        boolean isValidStatusId = statusValidos.stream()
                .anyMatch(status -> status.getId().equals(statusId));
        if (!isValidStatusId) {
            throw new IllegalArgumentException("Status ID " + statusId + " is not valid.");
        }
        return processoService.findByStatusProcessoId(statusId);
    }

    public Processo saveProcesso(Processo processo) {
        return processoService.save(processo);
    }

    public void deleteProcesso(Long id) {
        processoService.deleteById(id);
    }
}
