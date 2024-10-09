package com.ApiJuridica.ApiJuridica.Facades;

import com.ApiJuridica.ApiJuridica.Entities.Acao;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.AcaoDto;
import com.ApiJuridica.ApiJuridica.Entities.TipoAcao;
import com.ApiJuridica.ApiJuridica.Services.AcaoService;
import com.ApiJuridica.ApiJuridica.Services.TipoAcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AcaoFacade {

    private final AcaoService acaoService;
    private final TipoAcaoService tipoAcaoService;

    @Autowired
    public AcaoFacade(AcaoService acaoService, TipoAcaoService tipoAcaoService) {
        this.acaoService = acaoService;
        this.tipoAcaoService = tipoAcaoService;
    }

    public List<Acao> getAllAcoes() {
        return acaoService.getAllAcoes();
    }

    public List<Acao> getAcoesByProcessoId(Long processoId) {
        return acaoService.getAcoesByProcessoId(processoId);
    }

    public Acao getAcaoById(Long id) {
        return acaoService.getAcaoById(id);
    }

    public Acao saveAcao(AcaoDto acaoDto) {
        List<TipoAcao> tiposAcao = tipoAcaoService.getAll();
        boolean tipoAcaoExists = tiposAcao.stream()
                .anyMatch(tipo -> tipo.getId().equals(acaoDto.getTipoAcaoId()));
        if (!tipoAcaoExists) {
            throw new IllegalArgumentException("O ID do tipo de ação fornecido não existe.");
        }
        return acaoService.saveAcao(acaoDto);
    }

    public List<TipoAcao> getAllTipoAcao() {
        return tipoAcaoService.getAll();
    }

    public void deleteAcao(Long id) {
        acaoService.deleteAcao(id);
    }
}
