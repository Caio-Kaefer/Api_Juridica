package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.Dtos.ParteEnvolvidaDto;
import com.ApiJuridica.ApiJuridica.Entities.ParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Exceptions.NotFoundException;
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

    public ParteEnvolvida saveParteEnvolvida(ParteEnvolvidaDto parteEnvolvidaDto) {
        return parteRepository.save(parteEnvolvidaDto.toEntity());
    }

    public ParteEnvolvida updateParteEnvolvida(ParteEnvolvidaDto parteEnvolvidaDto, Long id) {
        if (!parteRepository.existsById(id)) {
            throw new NotFoundException("Parte Envolvida com ID " + id + " não encontrada.");
        }

        ParteEnvolvida parteEnvolvida = parteEnvolvidaDto.toEntity();
        parteEnvolvida.setId(id);
        return parteRepository.save(parteEnvolvida);
    }

    public void deleteParteEnvolvida(Long id) {
        if (!parteRepository.existsById(id)) {
            throw new NotFoundException("Parte Envolvida com ID " + id + " não encontrada.");
        }
        parteRepository.deleteById(id);
    }

    public List<ParteEnvolvida> getPartesByTipo(Long tipoId) {
        return parteRepository.findByTipoId(tipoId);
    }
}
