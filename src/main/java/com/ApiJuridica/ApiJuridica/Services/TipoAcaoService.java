package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.TipoAcao;
import com.ApiJuridica.ApiJuridica.Repositories.TipoAcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAcaoService {

    private final TipoAcaoRepository tipoAcaoRepository;

    @Autowired
    public TipoAcaoService(TipoAcaoRepository tipoAcaoRepository) {
        this.tipoAcaoRepository = tipoAcaoRepository;
    }
    public List<TipoAcao> getAll() {
        return tipoAcaoRepository.findAll();
    }


}
