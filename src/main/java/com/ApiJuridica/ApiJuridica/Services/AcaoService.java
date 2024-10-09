package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.Acao;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.AcaoDto;
import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Repositories.AcaoRepository;
import com.ApiJuridica.ApiJuridica.Repositories.ProcessoRepository;
import com.ApiJuridica.ApiJuridica.Exceptions.NotFoundException; // Import the custom exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcaoService {

    private final AcaoRepository acaoRepository;
    private final ProcessoRepository processoRepository;

    @Autowired
    public AcaoService(AcaoRepository acaoRepository, ProcessoRepository processoRepository) {
        this.acaoRepository = acaoRepository;
        this.processoRepository = processoRepository;
    }

    public List<Acao> getAllAcoes() {
        return acaoRepository.findAll();
    }

    public Acao getAcaoById(Long id) {
        return acaoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ação não encontrada com ID: " + id));
    }

    public List<Acao> getAcoesByProcessoId(Long processoId) {
        Processo processo = processoRepository.findById(processoId)
                .orElseThrow(() -> new NotFoundException("Processo não encontrado com ID: " + processoId));

        return acaoRepository.findAllByProcessoId(processo.getId());
    }

    public Acao saveAcao(AcaoDto acaoDto) {
        return acaoRepository.save(acaoDto.toEntity());
    }

    public void deleteAcao(Long id) {
        if (!acaoRepository.existsById(id)) {
            throw new NotFoundException("Ação não encontrada com ID: " + id);
        }
        acaoRepository.deleteById(id);
    }
}
