package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.Acao;
import com.ApiJuridica.ApiJuridica.Entities.TipoAcao;
import com.ApiJuridica.ApiJuridica.Repositories.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcaoService {

    private final AcaoRepository acaoRepository;

    @Autowired
    public AcaoService(AcaoRepository acaoRepository) {
        this.acaoRepository = acaoRepository;
    }

    public List<Acao> getAllAcoes() {
        return acaoRepository.findAll();
    }

    public Optional<Acao> getAcaoById(Long id) {
        return acaoRepository.findById(id);
    }

    public Acao saveAcao(Acao acao) {
        return acaoRepository.save(acao);
    }

    public void deleteAcao(Long id) {
        acaoRepository.deleteById(id);
    }
}
