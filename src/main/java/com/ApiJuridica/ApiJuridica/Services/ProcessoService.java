package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Repositories.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProcessoService {
    private final ProcessoRepository processoRepository;
    @Autowired
    public ProcessoService(ProcessoRepository processoRepository) {
        this.processoRepository = processoRepository;
    }

    public Optional<Processo> findByNumeroProcesso(String numeroProcesso) {
        return this.processoRepository.findByNumeroProcesso(numeroProcesso);
    }
}
