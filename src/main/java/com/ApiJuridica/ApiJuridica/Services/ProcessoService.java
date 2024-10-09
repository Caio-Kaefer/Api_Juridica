package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Repositories.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Processo> findAll() {
        return processoRepository.findAll();
    }

    public Processo save(Processo processo) {
        return processoRepository.save(processo);
    }

    public Processo update(Processo processo) {
        return processoRepository.save(processo);
    }

    public void deleteById(Long id) {
        if (!processoRepository.existsById(id)) {
            throw new IllegalArgumentException("Processo with ID " + id + " not found.");
        }
        processoRepository.deleteById(id);
    }

    public Optional<List<Processo>> findByStatusProcessoId(Long statusId) {
        return processoRepository.findByStatusProcessoId(statusId);
    }
}
