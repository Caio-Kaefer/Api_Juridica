package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.TipoParteEnvolvida;
import com.ApiJuridica.ApiJuridica.Exceptions.NotFoundException;
import com.ApiJuridica.ApiJuridica.Repositories.TIpoParteEnvolvidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoParteEnvolvidaService {

    private final TIpoParteEnvolvidaRepository tipoParteEnvolvidaRepository;

    @Autowired
    public TipoParteEnvolvidaService(TIpoParteEnvolvidaRepository tipoParteEnvolvidaRepository) {
        this.tipoParteEnvolvidaRepository = tipoParteEnvolvidaRepository;
    }

    public List<TipoParteEnvolvida> findAll() {
        return tipoParteEnvolvidaRepository.findAll();
    }

    public Optional<TipoParteEnvolvida> findById(Long id) {
        return tipoParteEnvolvidaRepository.findById(id);
    }

    public TipoParteEnvolvida save(TipoParteEnvolvida tipoParteEnvolvida) {
        return tipoParteEnvolvidaRepository.save(tipoParteEnvolvida);
    }

    public void deleteById(Long id) {
        if (!tipoParteEnvolvidaRepository.existsById(id)) {
            throw new NotFoundException("Tipo de Parte Envolvida com ID " + id + " não encontrado.");
        }
        tipoParteEnvolvidaRepository.deleteById(id);
    }
}
