package com.ApiJuridica.ApiJuridica.Entities.Dtos;

import com.ApiJuridica.ApiJuridica.Entities.Processo;

public class ProcessoDto {

    private String descricao;
    private Long statusProcessoId;

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

    public Processo toEntity() {
        Processo processo = new Processo();
        processo.setDescricao(descricao);
        processo.setStatusProjeto(statusProcessoId);
        processo.setNumeroProcesso(null);
        processo.setId(null);
        return processo;
    }
}
