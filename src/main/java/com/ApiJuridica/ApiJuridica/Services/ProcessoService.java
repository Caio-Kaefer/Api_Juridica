package com.ApiJuridica.ApiJuridica.Services;

import com.ApiJuridica.ApiJuridica.Entities.Dtos.ProcessoDto;
import com.ApiJuridica.ApiJuridica.Entities.Dtos.ProcessoUpdateDto;
import com.ApiJuridica.ApiJuridica.Entities.Processo;
import com.ApiJuridica.ApiJuridica.Exceptions.NotFoundException;
import com.ApiJuridica.ApiJuridica.Repositories.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Processo save(ProcessoDto processodto) {
        Processo processo = processodto.toEntity();
        processo.setNumeroProcesso(this.gerarNumeroProcesso());
        processo.setDataAbertura(LocalDate.now());
        return processoRepository.save(processo);
    }

    public Processo update(ProcessoUpdateDto processoDto, Long id) {
        // Verifica se o processo existe
        if (!this.processoRepository.existsById(id)) {
            throw new NotFoundException("Processo com ID " + id + " não encontrado.");
        }
        Processo processo = processoDto.toEntity();
        processo.setId(id);
        return processoRepository.save(processo);
    }

    public void deleteById(Long id) {
        // Verifica se o processo existe antes de deletar
        if (!processoRepository.existsById(id)) {
            throw new NotFoundException("Processo com ID " + id + " não encontrado.");
        }
        processoRepository.deleteById(id);
    }

    public Optional<List<Processo>> findByStatusProcessoId(Long statusId) {
        return processoRepository.findByStatusProcessoId(statusId);
    }

    public String gerarNumeroProcesso() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
