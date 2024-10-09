package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.StatusProcesso;
import com.ApiJuridica.ApiJuridica.Repositories.StatusProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusProcessoService {

    private final StatusProcessoRepository statusProcessoRepository;

    @Autowired
    public StatusProcessoService(StatusProcessoRepository statusProcessoRepository) {
        this.statusProcessoRepository = statusProcessoRepository;
    }

    public List<StatusProcesso> getAllStatus() {
        return statusProcessoRepository.findAll();
    }

}
