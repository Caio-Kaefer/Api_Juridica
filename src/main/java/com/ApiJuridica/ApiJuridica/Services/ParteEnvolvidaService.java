package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Repositories.ParteEnvolvidaRepository;
import com.ApiJuridica.ApiJuridica.Repositories.ParteEnvolvidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParteEnvolvidaService {

    private final ParteEnvolvidaRepository parteRepository;

    @Autowired
    public ParteEnvolvidaService(ParteEnvolvidaRepository parteRepository) {
        this.parteRepository = parteRepository;
    }

    public List<ParteEnvolvida> getAllPartesByProcessoId(Long processoId) {
        return parteRepository.findAllByProcessoId(processoId);
    }
}