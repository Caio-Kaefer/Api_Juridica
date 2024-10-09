package com.ApiJuridica.ApiJuridica.Entities.Dtos;

import com.ApiJuridica.ApiJuridica.Entities.Processo;

import java.time.LocalDate;

public class ProcessoUpdateDto {

    private String descricao;
    private Long statusProcessoId;
    private String numeroProcesso;;

    // Getters and Setters

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getStatusProcessoId() {
        return statusProcessoId;
    }

    public void setStatusProcessoId(Long statusProcessoId) {
        this.statusProcessoId = statusProcessoId;
    }


    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public Processo toEntity() {
        Processo processo = new Processo();
        processo.setDescricao(descricao);
        processo.setStatusProjeto(statusProcessoId);
        processo.setNumeroProcesso(numeroProcesso);
        processo.setDataAbertura(LocalDate.now());
        processo.setId(null);
        return processo;
    }
}
